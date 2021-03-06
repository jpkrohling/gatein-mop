/**
 * Copyright (C) 2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.gatein.mop.core.api.workspace;

import org.chromattic.api.annotations.FormattedBy;
import org.chromattic.api.annotations.NamingPrefix;
import org.chromattic.api.annotations.OneToMany;
import org.chromattic.api.annotations.OneToOne;
import org.chromattic.api.annotations.Create;
import org.chromattic.api.annotations.PrimaryType;
import org.chromattic.api.annotations.MappedBy;
import org.chromattic.ext.format.BaseEncodingObjectFormatter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
@PrimaryType(name = "mop:navigationcontainer")
@NamingPrefix("mop")
@FormattedBy(BaseEncodingObjectFormatter.class)
public abstract class NavigationContainer
{

   @OneToOne
   @MappedBy("mop:children")
   public abstract NavigationImpl getOwner();

   @OneToMany
   public abstract Map<String, NavigationImpl> getNavigationMap();

   @OneToMany
   public abstract List<NavigationImpl> getNavigationList();

   @Create
   public abstract NavigationImpl createNavigation();

   public NavigationImpl addNavigation(Integer index, String name)
   {
      NavigationImpl page = createNavigation();
      page.setNodeName(name);
      List<NavigationImpl> list = getNavigationList();
      if (index != null)
      {
         list.add(index, page);
      }
      else
      {
         list.add(page);
      }
      return page;
   }
}