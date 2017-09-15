package rahandaz.exilios.com.rahandaz.TaskHolderFragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.ArrayList;
import rahandaz.exilios.com.rahandaz.Models.DataModel;
import rahandaz.exilios.com.rahandaz.Models.ProjectModel;
import rahandaz.exilios.com.rahandaz.network.JsonParsers;
import rahandaz.exilios.com.rahandaz.network.NetworkManager;
import rahandaz.exilios.com.rahandaz.network.RequestPackage;

/**
 * Created by NaviD on 8/15/2017.
 */

public class TotalGetTaskHolderFragment extends Fragment {

    public interface TotalGetTaskCallbacks {
        void onPreExecute();
        void onProgressUpdate(int percent);
        void onCancelled();
        void onPostExecute(ArrayList<ProjectModel> result);
    }

    private TotalGetTaskCallbacks mCallbacks;
    private AsyncLoginService asyncLogin;

    /**
     * Hold a reference to the parent Activity so we can report the
     * task's current progress and results. The Android framework
     * will pass us a reference to the newly created Activity after
     * each configuration change.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (TotalGetTaskCallbacks) activity;
    }

    /**
     * This method will only be called once when the retained
     * Fragment is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain this fragment across configuration changes.
        setRetainInstance(true);
    }

    public void loginExecutor(RequestPackage p){
        asyncLogin = new AsyncLoginService();
        asyncLogin.execute(p);
    }

    /**
     * Set the callback to null so we don't accidentally leak the
     * Activity instance.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    /**
     * A dummy task that performs some (dumb) background work and
     * proxies progress updates and results back to the Activity.
     *
     * Note that we need to check if the callbacks are null in each
     * method in case they are invoked after the Activity's and
     * Fragment's onDestroy() method have been called.
     */
    private class AsyncLoginService extends AsyncTask<RequestPackage, Void, ArrayList<ProjectModel>> {

        @Override
        protected void onPreExecute() {
            if (mCallbacks != null) {
                mCallbacks.onPreExecute();
            }
        }

        /**
         * Note that we do NOT call the callback object's methods
         * directly from the background thread, as this could result
         * in a race condition.
         */
        @Override
        protected ArrayList<ProjectModel> doInBackground(RequestPackage... requestPackages) {

            String validation = "true";
//            try {
//                HttpURLConnection con = (HttpURLConnection) new URL("http://cdn.persiangig.com/preview/InOtQvmoAd/farsarCoSupport.txt").openConnection();
//                con.setRequestMethod("GET");
//                StringBuilder sb = new StringBuilder();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                while ((validation = reader.readLine()) != null) {
//                    if (sb.length() > 0)
//                        sb.append("\n");
//                    sb.append(validation);
//                }
//
//            } catch (ProtocolException e) {
//                e.printStackTrace();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            String data = NetworkManager.getData(requestPackages[0]);
//            Log.d("StringData", data);
                ArrayList<ProjectModel> response = JsonParsers.getDatamodels(data);
            return  response;
        }

        @Override
        protected void onPostExecute(ArrayList<ProjectModel>  result) {
            if (mCallbacks != null) {
                mCallbacks.onPostExecute(result);
            }
        }

        @Override
        protected void onProgressUpdate(Void... percent) {
            if (mCallbacks != null) {
                mCallbacks.onProgressUpdate(1);
            }
        }

        @Override
        protected void onCancelled() {
            if (mCallbacks != null) {
                mCallbacks.onCancelled();
            }
        }
    }
}
