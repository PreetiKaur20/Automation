package BasePage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NtlgReplace {
	  public void replceNtlg(String oldNtlg, String newNtlg) throws IOException {
          File file = new File("\\\\10.1.2.85\\Omega\\HCM_FEED_TESTING\\Finance\\Web.Config");
          BufferedReader reader = new BufferedReader(new FileReader(file));
          String line = "", oldtext = "";
          while ((line = reader.readLine()) != null) {
               oldtext += line + "\r\n";
          }
          System.out.println("Before replace*********");
          
          String newText = oldtext.replace(oldNtlg, newNtlg);
          System.out.println("after replce**********");
         
          FileWriter writer = new FileWriter(file);
          writer.write(newText);
          writer.close();
    }

}
