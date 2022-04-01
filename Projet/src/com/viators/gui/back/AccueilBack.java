 package com.viators.gui.back;

import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.viators.gui.LoginForm;

public class AccueilBack extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;
    public static AccueilBack accueilBack;

    public AccueilBack() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        accueilBack = this;

        addGUIs();
    }

    private void addGUIs() {
        ImageViewer userImage = new ImageViewer(theme.getImage("default.jpg").fill(200, 200));
        userImage.setUIID("candidatImage");
        label = new Label("Admin"/*MainApp.getSession().getEmail()*/);
        label.setUIID("links");
        Button btnDeconnexion = new Button();
        btnDeconnexion.setUIID("buttonLogout");
        btnDeconnexion.setMaterialIcon(FontImage.MATERIAL_ARROW_FORWARD);
        btnDeconnexion.addActionListener(action -> {
            LoginForm.instance.showBack();
        });

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.WEST, userImage);
        userContainer.add(BorderLayout.CENTER, label);
        userContainer.add(BorderLayout.EAST, btnDeconnexion);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makeHotelButton(),
                makeRoomsButton()
        );

        this.add(menuContainer);
    }

    private Button makeHotelButton() {
        Button button = new Button("    Hotels");
        button.setUIID("buttonMenu");
        button.setMaterialIcon( FontImage.MATERIAL_HOTEL);
        button.addActionListener(action -> {
            new com.viators.gui.back.hotel.DisplayAll().show();
        });
        return button;
    }
    
        private Button makeRoomsButton() {
        Button button = new Button("    Chambres");
        button.setUIID("buttonMenu");
        button.setMaterialIcon( FontImage.MATERIAL_BED);
        button.addActionListener(action -> {
            new com.viators.gui.back.chambre.DisplayAll().show();
        });
        return button;
    }
}
