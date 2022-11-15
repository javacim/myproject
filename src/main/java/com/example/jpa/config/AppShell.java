package com.example.jpa.config;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.component.page.Meta;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Meta(name = "Author", content = "Micha Schirmer")
@PWA(name = "My Fun Application", shortName = "fun-app")
@Viewport("width=device-width, initial-scale=1")
@BodySize(height = "100vh", width = "100vw")
@PageTitle("my-title")
@Theme(variant = Lumo.DARK)
public class AppShell implements AppShellConfigurator {

    //https://vaadin.com/api/platform/23.2.8/com/vaadin/flow/component/page/AppShellConfigurator.html


}
