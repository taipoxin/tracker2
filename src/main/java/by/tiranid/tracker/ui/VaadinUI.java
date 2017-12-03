package by.tiranid.tracker.ui;

import by.tiranid.tracker.dao.model.WorkDaysEntity;
import by.tiranid.tracker.dao.model.WorkItersEntity;
import by.tiranid.tracker.dao.repository.WorkDaysRepository;
import by.tiranid.tracker.dao.repository.WorkItersRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {


    @Autowired
    private WorkItersRepository workItersRepository;
    @Autowired
    private WorkDaysRepository workDaysRepository;


    private Grid<WorkItersEntity> itersGrid;
    private Grid<WorkDaysEntity> daysGrid;


    public VaadinUI(WorkItersRepository iters, WorkDaysRepository days) {

        this.itersGrid = new Grid<>(WorkItersEntity.class);
        this.daysGrid = new Grid<>(WorkDaysEntity.class);
    }

    @Override
    protected void init(VaadinRequest request) {
        // to place grid on the center
        VerticalLayout vLayout = new VerticalLayout();
        CenterPanel itersPanel = new CenterPanel(itersGrid);
        //CenterPanel daysPanel = new CenterPanel(daysGrid);
        vLayout.addComponent(itersPanel);
        //vLayout.addComponent(daysPanel);
        vLayout.setSizeFull();
        vLayout.setComponentAlignment(itersPanel, Alignment.MIDDLE_CENTER);
        //vLayout.setComponentAlignment(daysPanel, Alignment.MIDDLE_CENTER+100);

        this.setContent(vLayout);
        listCustomers();
    }

    private void listCustomers() {
        itersGrid.setItems(workItersRepository.findAll());
        daysGrid.setItems(workDaysRepository.findAll());
    }

    class CenterPanel extends Panel {

        public CenterPanel(Component content) {
            this.setSizeUndefined();
            this.setContent(content);
        }
    }
}
