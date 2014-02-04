package com.loginform;

import java.io.IOException;
import java.io.InputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DOMXMLParsingActivity extends Activity implements OnClickListener {
    TextView techText;
 
    Button button;
 
    static final String NODE_TART = "tartalom";
    static final String NODE_TECH = "technikai";
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        findViewsById();
        
        button.setOnClickListener(this);
    }
    
    private void findViewsById() {
        techText = (TextView) findViewById(R.id.techText);
        button = (Button) findViewById(R.id.button);
    }
    public void onClick(View v) {
        XMLDOMParser parser = new XMLDOMParser();
        AssetManager manager = getAssets();
        InputStream stream;
        try {
            stream = manager.open("technikai.xml");
            Document doc = parser.getDocument(stream);
 
            NodeList nodeList = doc.getElementsByTagName(NODE_TART);
 
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element e = (Element) nodeList.item(i);
                techText.setText(parser.getValue(e, NODE_TECH));
 
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
}