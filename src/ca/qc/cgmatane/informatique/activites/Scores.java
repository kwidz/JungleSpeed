package ca.qc.cgmatane.informatique.activites;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ca.qc.cgmatane.informatique.Client.ClientNonXML;
import com.example.junglerapide.R;
import android.app.Activity;
import org.jdom2.Document;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class Scores extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
        ClientNonXML client = new ClientNonXML();
        Document document = client.retournerLesScores();
        ArrayList<String> lesScores = new ArrayList<String>();
        Element elementParent = document.getRootElement();
        Log.e("Element",elementParent.toString());
        List<Element> listeDeScores = elementParent.getChildren("score");
        for(int i=0;i<=listeDeScores.size()-1;i++){
            Element element = listeDeScores.get(i);
            Log.e("Pseudonyme",element.getChildText("pseudo") + "\t");
            Log.e("Score",element.getChildText("pointage"));
            lesScores.add(new String(element.getChildText("pseudo")+" : "+element.getChildText("pointage")));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesScores);
        ListView listeDesScores=findViewById()
        setListAdapter(adapter);

    }

}
