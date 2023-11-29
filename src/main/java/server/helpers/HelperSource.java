package server.helpers;

import com.github.jknack.handlebars.Options;

import java.util.Arrays;

public class HelperSource {

    public String breadcrumb(String route){
        String[] array= route.substring(1).split("/");
        String result="";

        for(Integer i=0;i<array.length-1;i++){
            String rutaItem= String.join("/",Arrays.copyOfRange(array,0,i+1));

            result+="<li class=\"breadcrumb-item\"><a class=\"link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover\" " +
                    "href=\"/" +rutaItem+"\">"+array[i]+"</a></li>";
        }
        result+="<li class=\"breadcrumb-item active text-secondary\" aria-current=\"page\">"+array[array.length-1]+"</li>";
        return result;
    }

}
