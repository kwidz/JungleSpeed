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
        Document document = new Document();
        client.envoyerScore("eeee",12);

        ArrayList<String> lesScores = new ArrayList<String>();
        Element elementParent = document.getRootElement();
        List<Element> listeDeScores = elementParent.getChildren("scores");
        for(int i=0;i<=listeDeScores.size()-1;i++){
            Element element = listeDeScores.get(i);
            System.out.print("Pseudo : "+element.getChildText("pseudo")+"\t");
            System.out.println("Score: " + element.getChildText("pointage"));
        }

    }

}
