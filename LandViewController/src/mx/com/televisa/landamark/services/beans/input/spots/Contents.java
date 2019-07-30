package mx.com.televisa.landamark.services.beans.input.spots;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Contents {
    public Contents() {
        super();
    }
    
    @XmlElement(name = "Content")
    private List<Content> Content;

    public void setContent(List<Content> Content) {
        this.Content = Content;
    }

    public List<Content> getContent() {
        return Content;
    }
}
