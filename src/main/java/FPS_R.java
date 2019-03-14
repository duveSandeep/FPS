import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FPS_R
{
    public static void main(String[] args) throws IOException
    {




        FileInputStream fstream = new FileInputStream("C:\\Users\\sandeep.duve\\Downloads\\storeSense.log");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        ArrayList<Long> timeStamp = new ArrayList<Long>();
        ArrayList<Double> fps = new ArrayList<Double>();

        String strTimeStamp = "TimeStamp:";
        String strEventTimeStamp = "Event Timestamp:";
        String strFPS = "FPS:";

        String s;
        InputStreamReader r = new InputStreamReader(fstream, "UTF-8"); //leave charset out for default
        br = new BufferedReader(r);
        while ((s = br.readLine()) != null)
        {

            if (s.contains(strFPS))
            {

                timeStamp.add(Long.parseLong(StringUtils.substringBetween(s, strTimeStamp, ",").trim()));
                fps.add(Double.parseDouble(StringUtils.substringAfter(s, strFPS).trim()));
            }

            if (s.contains(strEventTimeStamp))
            {
                System.out.println(s);

                long tmpTimeStamp = Long.parseLong(StringUtils.substringAfter(s, strEventTimeStamp).trim());

                int tmpCount = fps.size();
                Double tmpFPS = 0d;
                int tmpAvgCount = 0;
                for (int i = tmpCount - 1; i >= 0; i--)
                {

                    if (tmpTimeStamp <= timeStamp.get(i))
                    {
                        tmpFPS += fps.get(i);
                        tmpAvgCount++;
                    }
                }

                System.out.println("tmp avg count: " + tmpAvgCount);
                System.out.println("Avg: " + (tmpFPS / tmpAvgCount));
                timeStamp = new ArrayList<Long>();
                fps = new ArrayList<Double>();
            }
        }
    }
}