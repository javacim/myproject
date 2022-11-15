package com.example.jpa.view;


import com.example.jpa.model.User;
import com.example.jpa.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolationException;
import java.util.Locale;


@Route("/") //default   http://localhost:8080/
public class MainView extends VerticalLayout {

    Binder<User> binder = new BeanValidationBinder<>(User.class);//Validation *********************
    // @Autowired   //DependencyInjection - @Service
    private UserService service;
    private Grid<User> table = new Grid<>(User.class);

    @Autowired  //DependencyInjection
    public MainView(UserService service) {// Achtung: Constructor Inject
        UI.getCurrent().getSession().setLocale(Locale.GERMANY);//auf aktuelle UI-Instanz zugreifen
        this.service = service;
        table.setColumns("id", "firstname", "lastname", "email", "dateOfBirth");
        table.setItems(service.getUsers());



        add(createSaveBar());
        add(table);
        table.addItemDoubleClickListener(e -> {
            createEditDialog(e.getItem()).open();
        });

        //table.addContextMenu()
        GridContextMenu<User> menu = table.addContextMenu();

        menu.addItem("Edit", event -> {
            createEditDialog(event.getItem().get()).open();
        });
        menu.addItem("Delete", event -> {
            User user = event.getItem().get();
            service.delete(user.getId());
            table.setItems(service.getUsers());
        });

    }


    @PostConstruct // wird nach Konstruktor ausgeführt
    public void init() {


    }


    public HorizontalLayout createSaveBar() {
        var hz = new HorizontalLayout();
        var firstnameField = new TextField();
        firstnameField.setPlaceholder("First Name");

        var lastnameField = new TextField();
        lastnameField.setPlaceholder("Last Name");
        binder.forField(lastnameField).asRequired()
                .withValidator( name-> name.length()>1,"Nachname mindestens 2 Zeichen!")
                .bind(User::getLastname,User::setLastname);

        var emailField = new EmailField();
        emailField.setPlaceholder("E-Mail");
        binder.forField(emailField).asRequired()
                .withValidator(new EmailValidator("Keine gültige E-Mail"))
                .bind(User::getEmail,User::setEmail);


        var datePicker = new DatePicker();
        datePicker.setPlaceholder("Date of Birth");
        var b = new Button("Save");

        hz.add(firstnameField, lastnameField, emailField, datePicker, b);

        ///////////////// Save /////////////////////////////////
        b.addClickListener(e -> {

            try {

                System.out.println("save...");
                User saveUser = new User(firstnameField.getValue(), lastnameField.getValue(), emailField.getValue(), datePicker.getValue());
                var saved = service.save(saveUser);
                if (saved != null) {
                    Notification.show("Daten gespeichert!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                }
                table.setItems(service.getUsers());//refresh
            } catch (ConstraintViolationException ve) {// Model Validierung -Hibernate
                System.out.println(ve.getConstraintViolations());
                Notification notification = Notification.show("Alle Felder korrekt ausfüllen!");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }


        });

        return hz;

    }

    public Dialog createEditDialog(User currentUser) {

        var dialog = new Dialog();
        dialog.setHeaderTitle("Edit");


        var firstnameField = new TextField("First Name");
        firstnameField.setValue(currentUser.getFirstname());

        var lastnameField = new TextField("Last Name");
        lastnameField.setValue(currentUser.getLastname());
        var emailField = new EmailField("E-Mail");
        emailField.setValue(currentUser.getEmail());

        var datePicker = new DatePicker("E-Mail");
        datePicker.setLocale(Locale.GERMAN);
        datePicker.setValue(currentUser.getDateOfBirth());

        var saveButton = new Button("Save");
        saveButton.addClickListener(e -> {
            System.out.println("update...");
            service.update(new User(currentUser.getId(), firstnameField.getValue(), lastnameField.getValue(), emailField.getValue(), datePicker.getValue()));
            table.setItems(service.getUsers());//refresh
            dialog.close();

        });


        dialog.add(firstnameField, lastnameField, emailField, datePicker);
        dialog.getFooter().add(saveButton);
        return dialog;

    }


}
