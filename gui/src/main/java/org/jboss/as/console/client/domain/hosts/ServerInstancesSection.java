/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.jboss.as.console.client.domain.hosts;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.as.console.client.Console;
import org.jboss.as.console.client.core.NameTokens;
import org.jboss.ballroom.client.layout.LHSNavTree;
import org.jboss.ballroom.client.layout.LHSNavTreeItem;
import org.jboss.ballroom.client.util.Places;
import org.jboss.ballroom.client.widgets.stack.DisclosureStackPanel;

/**
 * @author Heiko Braun
 * @date 3/4/11
 */
public class ServerInstancesSection implements HostSelectionEvent.HostSelectionListener{


    private String selectedHost = null;
    private DisclosurePanel panel;

    private LHSNavTree instanceTree;

    public ServerInstancesSection() {

        panel = new DisclosureStackPanel(Console.CONSTANTS.common_label_runtimeStatus()).asWidget();

        instanceTree = new LHSNavTree("hosts");

        LHSNavTreeItem status = new LHSNavTreeItem(Console.CONSTANTS.common_label_serverInstances(), new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event) {
                String token = buildToken(NameTokens.InstancesPresenter);
                Console.MODULES.getPlaceManager().revealPlaceHierarchy(
                        Places.fromString(token)
                );
            }
        });

        instanceTree.addItem(status);

        /*LHSNavTreeItem vmView = new LHSNavTreeItem(Console.CONSTANTS.common_label_hostVm(), new ClickHandler()
               {
                   @Override
                   public void onClick(ClickEvent event) {
                       String token = buildToken(NameTokens.HostVMMetricPresenter);
                       Console.MODULES.getPlaceManager().revealPlaceHierarchy(
                               Places.fromString(token)
                       );
                   }
               });

        instanceTree.addItem(vmView);*/

        final ClickHandler noop = new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

            }
        };

        /*LHSNavTreeItem deployments = new LHSNavTreeItem("Deployed Applications", noop);
        instanceTree.addItem(deployments);

        LHSNavTreeItem subsystems = new LHSNavTreeItem("Subsystem State", noop);
        instanceTree.addItem(subsystems);*/

        // listen on host selection events
        Console.MODULES.getEventBus().addHandler(
                HostSelectionEvent.TYPE, this
        );

        panel.setContent(instanceTree);
    }

    public Widget asWidget()
    {
        return panel;
    }

    @Override
    public void onHostSelection(String hostName) {
        selectedHost = hostName;
    }

    public void setSelectedHost(String hostName) {
        this.selectedHost = hostName;
    }

    private String buildToken(String presenter) {
        assert selectedHost!=null : "host selection is null!";
        final String token = "hosts/" +presenter+";host="+selectedHost;
        return token;
    }
}
