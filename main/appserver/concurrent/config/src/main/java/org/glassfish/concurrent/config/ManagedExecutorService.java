/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.concurrent.config;

import com.sun.enterprise.config.serverbeans.BindableResource;
import com.sun.enterprise.config.serverbeans.Resource;
import com.sun.enterprise.config.serverbeans.customvalidators.ReferenceConstraint;
import org.glassfish.admin.cli.resources.ResourceConfigCreator;
import org.glassfish.api.admin.RestRedirect;
import org.glassfish.api.admin.RestRedirects;
import org.glassfish.admin.cli.resources.UniqueResourceNameConstraint;
import org.jvnet.hk2.config.*;
import org.jvnet.hk2.config.types.Property;
import org.jvnet.hk2.config.types.PropertyBag;
import org.glassfish.resourcebase.resources.ResourceTypeOrder;
import org.glassfish.resourcebase.resources.ResourceDeploymentOrder;

import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.beans.PropertyVetoException;
import java.util.List;

/**
 * Concurrency managed executor service resource definition
 */

@Configured
@ResourceConfigCreator(commandName="create-managed-executor-service")
@RestRedirects({
 @RestRedirect(opType = RestRedirect.OpType.POST, commandName = "create-managed-executor-service"),
 @RestRedirect(opType = RestRedirect.OpType.DELETE, commandName = "delete-managed-executor-service")
})
@ResourceTypeOrder(deploymentOrder=ResourceDeploymentOrder.MANAGED_EXECUTOR_SERVICE)
@ReferenceConstraint(skipDuringCreation=true, payload=ManagedExecutorService.class)
@UniqueResourceNameConstraint(message="{resourcename.isnot.unique}", payload=ManagedExecutorService.class)
public interface ManagedExecutorService extends ConfigBeanProxy, Resource,
        PropertyBag, BindableResource, Payload {

    /**
     * Gets the value of the contextServiceName property.
     *
     * @return possible object is
     *         {@link String }
     *        
     */
    @Attribute
    @NotNull
    @ReferenceConstraint.RemoteKey(message="{resourceref.invalid.contextservicename}", type=ContextService.class)
    String getContextServiceName();

    /**
     * Sets the value of the contextServiceName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setContextServiceName(String value) throws PropertyVetoException;

    /**
     * Gets the value of the managedThreadFactoryName property.
     *
     * @return possible object is
     *         {@link String }
     *
     */
    @Attribute
    @NotNull
    @ReferenceConstraint.RemoteKey(message="{resourceref.invalid.managedthreadfactoryname}", type=ManagedThreadFactory.class)
    String getManagedThreadFactoryName();

    /**
     * Sets the value of the managedThreadFactoryName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setManagedThreadFactoryName(String value) throws PropertyVetoException;

    /**
     * Gets the value of the longRunningTasks property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute(defaultValue="false", dataType=Boolean.class)
    String getLongRunningTasks();

    /**
     * Sets the value of the longRunningTasks property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setLongRunningTasks(String value) throws PropertyVetoException;
    
    /**
     * Gets the value of the hungAfterSeconds property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute(dataType=Integer.class)
    String getHungAfterSeconds();

    /**
     * Sets the value of the hungAfterSeconds property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setHungAfterSeconds(String value) throws PropertyVetoException;

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute
    String getDescription();

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setDescription(String value) throws PropertyVetoException;

    /**
        Get the value of the poolInfo property.
     */
    @Element
    PoolInfo getPoolInfo();

    /**
     * Sets the value of the poolInfo property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setPoolInfo(PoolInfo value) throws PropertyVetoException;


    /**
        Properties as per {@link PropertyBag}
     */
    @Element
    List<Property> getProperty();

}
