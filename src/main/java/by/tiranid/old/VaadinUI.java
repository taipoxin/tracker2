package by.tiranid.old;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

//@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        setContent(new Button("Че пацаны?", e -> Notification.show("Аниме")));
    }
}