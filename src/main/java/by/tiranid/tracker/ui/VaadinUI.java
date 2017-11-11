package by.tiranid.tracker.ui;

import by.tiranid.tracker.dao.model.WorkDaysEntity;
import by.tiranid.tracker.dao.model.WorkItersEntity;
import by.tiranid.tracker.dao.repositories.WorkDaysRepository;
import by.tiranid.tracker.dao.repositories.WorkItersRepository;
import by.tiranid.tracker.dao.service.WorkDaysService;
import by.tiranid.tracker.dao.service.WorkItersService;
import by.tiranid.tracker.dao.service.impl.WorkDaysServiceImpl;
import by.tiranid.tracker.dao.service.impl.WorkItersServiceImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {


    private WorkItersService workItersService;
    private WorkDaysService workDaysService;


    private Grid<WorkItersEntity> itersGrid;
    private Grid<WorkDaysEntity> daysGrid;

    @Autowired
    public VaadinUI(WorkItersRepository iters, WorkDaysRepository days) {
        workItersService = new WorkItersServiceImpl(iters);
        workDaysService = new WorkDaysServiceImpl(days);

        this.itersGrid = new Grid<>(WorkItersEntity.class);
        this.daysGrid = new Grid<>(WorkDaysEntity.class);
    }

    @Override
    protected void init(VaadinRequest request) {
        // to place grid on the center
        VerticalLayout vLayout = new VerticalLayout();
        CenterPanel itersPanel = new CenterPanel(itersGrid);
        CenterPanel daysPanel = new CenterPanel(daysGrid);
        vLayout.addComponent(itersPanel);
        vLayout.addComponent(daysPanel);
        vLayout.setSizeFull();
        vLayout.setComponentAlignment(itersPanel, Alignment.MIDDLE_CENTER);
        vLayout.setComponentAlignment(daysPanel, Alignment.MIDDLE_CENTER);

        this.setContent(vLayout);
        listCustomers();
    }

    private void listCustomers() {
        itersGrid.setItems(workItersService.getAll());
        daysGrid.setItems(workDaysService.getAll());
    }

    class CenterPanel extends Panel {

        public CenterPanel(Component content) {
            this.setSizeUndefined();
            this.setContent(content);
        }
    }
}
