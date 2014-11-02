package ca.qc.cgmatane.informatique.activites;

import android.os.Bundle;
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
        Element rootNode = document.getRootElement();
        List<Element> listeDeScores = rootNode.getChildren("scores");

    }

}
