package demo.asynctask;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * very simple demo of a AsyncTask.
 * Starts a AsyncTask to to display the progress from 0 to 100 (in increments of 5).
 */
public class MainActivity extends AppCompatActivity {
    TextView Progress;
    Button Button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Progress = findViewById(R.id.textView1);
        Button1 = findViewById(R.id.button1);
        Button1.setOnClickListener(new Button.OnClickListener() {
            /**
             * starts the asynctask.
             */
            @Override
            public void onClick(View view) {
                CountingTask task = new CountingTask();
                task.execute(0);
            }
        });
    }

    /**
     * This is a very simple AsyncTask that counts to 100 and sets to the text view in the
     * layout.
     */
    public class CountingTask extends AsyncTask<Integer, Integer, Integer> {

        CountingTask() {
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            int i = params[0];
            while (i < 100) {
                SystemClock.sleep(250);
                i++;
                if (i % 5 == 0) {
                    //update UI
                    publishProgress(i);
                }
            }
            return i;
        }

        protected void onProgressUpdate(Integer... progress) {
            Progress.setText("Progress: " + progress[0] + "%");
        }

        protected void onPostExecute(Integer result) {
            Progress.setText("Completed: " + result + "%");
        }
    }
}