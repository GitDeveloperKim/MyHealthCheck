package com.example.myapplication01.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import com.example.myapplication01.R
import com.example.myapplication01.databinding.ActivityLoginBinding
import com.example.myapplication01.util.PreferenceManager
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val credentialManager by lazy { CredentialManager.create(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 이미 로그인되어 있으면 다음 화면으로
        if (PreferenceManager.isLoggedIn(this)) {
            goToNext()
            return
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.googleSignInButton.setOnClickListener { signInWithGoogle() }
    }

    private fun signInWithGoogle() {
        val webClientId = getString(R.string.default_web_client_id)
        if (webClientId.startsWith("YOUR_")) {
            Toast.makeText(this, "strings.xml 의 default_web_client_id 를 설정하세요", Toast.LENGTH_LONG).show()
            return
        }

        // "Google 계정으로 로그인" 버튼 플로우 (항상 계정 선택 화면 표시)
        val googleOption = GetSignInWithGoogleOption.Builder(webClientId).build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleOption)
            .build()

        setLoading(true)
        lifecycleScope.launch {
            try {
                val result = credentialManager.getCredential(this@LoginActivity, request)
                handleSignIn(result)
            } catch (e: GetCredentialCancellationException) {
                // 계정을 선택했는데도 여기로 오면 대부분 Cloud Console 의 OAuth 설정 문제
                // (Android 클라이언트의 패키지명/SHA-1 불일치, 웹 클라이언트 ID 오류, 테스트 사용자 미등록)
                Log.w(TAG, "로그인 취소됨 (type=${e.type}, message=${e.message})", e)
                Toast.makeText(this@LoginActivity, "로그인이 취소되었습니다: ${e.message ?: "원인 불명"}", Toast.LENGTH_LONG).show()
            } catch (e: GetCredentialException) {
                Log.e(TAG, "로그인 실패", e)
                Toast.makeText(this@LoginActivity, "로그인 실패: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                setLoading(false)
            }
        }
    }

    private fun handleSignIn(result: GetCredentialResponse) {
        val credential = result.credential
        if (credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            try {
                val googleCredential = GoogleIdTokenCredential.createFrom(credential.data)
                // googleCredential.idToken 은 서버(또는 Firebase)에서 검증할 때 사용
                PreferenceManager.saveUser(
                    this,
                    email = googleCredential.id,
                    name = googleCredential.displayName,
                    photoUrl = googleCredential.profilePictureUri?.toString()
                )
                Toast.makeText(this, "${googleCredential.displayName ?: googleCredential.id} 님 환영합니다", Toast.LENGTH_SHORT).show()
                goToNext()
            } catch (e: GoogleIdTokenParsingException) {
                Log.e(TAG, "ID 토큰 파싱 실패", e)
                Toast.makeText(this, "로그인 정보를 읽을 수 없습니다", Toast.LENGTH_SHORT).show()
            }
        } else {
            Log.e(TAG, "알 수 없는 credential 타입: ${credential.type}")
        }
    }

    private fun setLoading(loading: Boolean) {
        binding.googleSignInButton.isEnabled = !loading
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    /** 첫 로그인(신체 정보 미입력)이면 입력 화면으로, 아니면 메인으로 이동 */
    private fun goToNext() {
        val next = if (PreferenceManager.isProfileCompleted(this)) {
            MainActivity::class.java
        } else {
            ProfileSetupActivity::class.java
        }
        startActivity(Intent(this, next))
        finish()
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}
