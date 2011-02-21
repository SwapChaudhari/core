package org.jboss.as.console.client.domain.groups;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import org.jboss.as.console.client.domain.model.ServerGroupRecord;


public class ServerGroupCell extends AbstractCell<ServerGroupRecord> {

    interface Template extends SafeHtmlTemplates {
        @Template("<div class=\"{0}\" style=\"outline:none;\" ><b>{1}</b> ({2})</div>")
        SafeHtml message(String cssClass, String from, String message);
    }

    private static final Template TEMPLATE = GWT.create(Template.class);

    @Override
    public void render(Context context, ServerGroupRecord value, SafeHtmlBuilder sb) {
        sb.append(TEMPLATE.message(
                "cell-record",
                value.getAttribute("group-name"),
                value.getAttribute("profile-name"))
        );
    }
}
