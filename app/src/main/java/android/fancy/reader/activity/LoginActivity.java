package android.fancy.reader.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.fancy.reader.FancyReaderApplication;
import android.support.design.widget.Snackbar;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import android.fancy.reader.R;

import android.fancy.reader.observable.entity.TResult;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginActivity extends AppCompatActivity{


    private static final int REQUEST_READ_CONTACTS = 0;
    // UI references.
    private AutoCompleteTextView mUserNameTV;
    private EditText mPasswordTV;
    private View mProgressView;
    private View mLoginOrSignUpFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupActionBar();
        // Set up the login form.
        mUserNameTV = (AutoCompleteTextView) findViewById(R.id.user_name);

        mPasswordTV = (EditText) findViewById(R.id.password);
        mPasswordTV.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });

        Button loginButton = (Button) findViewById(R.id.login_in_button);
        Button signUpButton = (Button) findViewById(R.id.sign_up_button);
        assert loginButton != null;
        loginButton.setOnClickListener(view -> attemptLogin());
        signUpButton.setOnClickListener(view -> attemptSignUp());

        mLoginOrSignUpFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void attemptSignUp() {
        showProgress(true);
        FancyReaderApplication.getApiService().register(
                mUserNameTV.getText().toString(),
                mPasswordTV.getText().toString()
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(booleanTResult -> {
                    if (!booleanTResult.getResult()) {
                        throw new RuntimeException("注册失败，用户名已经存在");
                    }
                    return booleanTResult;
                })
                .subscribe(new Subscriber<TResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        showProgress(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showProgress(false);
                        Snackbar.make(mLoginOrSignUpFormView, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(TResult<Boolean> booleanTResult) {
                        Snackbar.make(mLoginOrSignUpFormView, "注册成功", Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    private void attemptLogin() {

        showProgress(true);
        FancyReaderApplication.getApiService().login(
                mUserNameTV.getText().toString(),
                mPasswordTV.getText().toString()
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(booleanTResult -> {
                    if (!booleanTResult.getResult()) {
                        throw new RuntimeException("登录失败，请检查用户名或密码");
                    }
                    return booleanTResult;
                })
                .subscribe(new Subscriber<TResult<Boolean>>() {
                    @Override
                    public void onCompleted() {
                        showProgress(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showProgress(false);
                        Snackbar.make(mLoginOrSignUpFormView, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(TResult<Boolean> booleanTResult) {
                        Snackbar.make(mLoginOrSignUpFormView, "登录成功", Snackbar.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginOrSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginOrSignUpFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginOrSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginOrSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


}

