/*******************************************************************************
 * Copyright (c) 2001-2012 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 *
 * Contributors:
 *     Pavel Savara
 *     - Initial implementation
 *******************************************************************************/
package net.sf.robocode.repository.items.handlers;


import net.sf.robocode.repository.IRepository;
import net.sf.robocode.repository.items.IRepositoryItem;
import net.sf.robocode.repository.items.RobotItem;
import net.sf.robocode.repository.root.IRepositoryRoot;

import java.net.URL;


/**
 * Handler for accepting and registering .class files.
 *
 * @author Pavel Savara (original)
 */
public class ClassHandler extends ItemHandler {
	public IRepositoryItem acceptItem(URL itemURL, IRepositoryRoot root, IRepository repository) {
		final String name = itemURL.toString().toLowerCase();

		if (name.endsWith(".class") && !name.contains("$")) {
			return register(itemURL, root, repository);
		}
		return null;
	}

	private IRepositoryItem register(URL itemURL, IRepositoryRoot root, IRepository repository) {
		RobotItem item = (RobotItem) repository.getItem(itemURL.toString());

		if (item == null) {
			item = new RobotItem(itemURL, root);
		}
		item.setClassPathURL(root.getURL());
		item.setClassURL(itemURL);

		repository.addOrUpdateItem(item);
		return item;
	}
}
