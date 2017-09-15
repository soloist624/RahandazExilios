package rahandaz.exilios.com.rahandaz.network;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rahandaz.exilios.com.rahandaz.Models.DataModel;
import rahandaz.exilios.com.rahandaz.Models.ProjectModel;

/**
 * Created by NaviD on 8/15/2017.
 */

public class JsonParsers {


    public static Context context;
    public static ArrayList<ProjectModel> getDatamodels (String content) {

        ArrayList<ProjectModel> projectModels = new ArrayList<>();
        ProjectModel model;
        try {
            JSONArray array = new JSONArray(content);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                model = new ProjectModel();
                model.setTitle(obj.getString("title"));
                model.setRemainingTime(obj.getString("dead_line"));
                model.setGoalAmount(obj.getInt("need_price"));
                model.setCurrentAmount(obj.getInt("fund_sum"));
                model.setDescription(obj.getString("description"));
                model.setImageUrl(obj.getString("image"));
                JSONArray a = new JSONArray(obj.getString("creators"));
                JSONObject o = a.getJSONObject(0);
                model.setCreator(o.getString("name"));
                projectModels.add(model);
            }

            return projectModels;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
