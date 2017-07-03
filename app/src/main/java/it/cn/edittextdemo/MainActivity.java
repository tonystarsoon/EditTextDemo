package it.cn.edittextdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private InputMethodManager mManager;
    private EditText mView1;
    private EditText mView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        mView1 = (EditText) findViewById(R.id.edit_query1);
        mView2 = (EditText) findViewById(R.id.edit_query2);
        mView1.requestFocus();
        mView1.setLongClickable(false);//让EditTextView不能长安复制黏贴
        mView2.setLongClickable(false);

        /*mView1.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowing) {
                    hideInput();
                } else {
                    showInput();
                }
                isShowing = !isShowing;
                int softInputMode = getWindow().getAttributes().softInputMode;
                Log.i(TAG, "onClick: ------" + softInputMode);
            }
        });
    }


    private boolean isShowing = true;

    private void showInput() {
        mManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void hideInput() {
        mManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//        mManager.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), 0);
//        mManager.toggleSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View currentFocus = getCurrentFocus();
        Log.i(TAG, "onClick: ------" + currentFocus);
        return super.onTouchEvent(event);
    }
}






