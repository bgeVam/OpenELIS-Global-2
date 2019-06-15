/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package us.mn.state.health.lims.common.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.role.RoleService;
import spring.service.rolemodule.RoleModuleService;
import spring.service.systemmodule.SystemModuleService;
import spring.util.SpringContext;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;

/**
 */
@Service
@DependsOn({ "springContext" })
public class PluginPermissionService implements IPluginPermissionService {

	@Autowired
	private SystemModuleService moduleService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleModuleService roleModuleService;

	public static IPluginPermissionService getInstance() {
		return SpringContext.getBean(IPluginPermissionService.class);
	}

	@Override
	public SystemModule getOrCreateSystemModule(String action, String description) {
		return getOrCreateSystemModuleByName(action, description);
	}

	@Override
	public SystemModule getOrCreateSystemModule(String action, String type, String description) {
		return getOrCreateSystemModuleByName(action + ":" + type, description);
	}

	private SystemModule getOrCreateSystemModuleByName(String name, String description) {
		SystemModule module = moduleService.getSystemModuleByName(name);

		if (module == null || module.getId() == null) {
			module = new SystemModule();
			module.setSystemModuleName(name);
			module.setDescription(description);
			module.setHasAddFlag("Y");
			module.setHasDeleteFlag("Y");
			module.setHasSelectFlag("Y");
			module.setHasUpdateFlag("Y");
			module.setSysUserId("1");
		}
		return module;
	}

	@Override
	public Role getSystemRole(String name) {
		return roleService.getRoleByName(name);
	}

	@Override
	@Transactional
	public boolean bindRoleToModule(Role role, SystemModule module) {
		if (role == null || module == null) {
			return false;
		}

		if (role.getId() == null) {
			role.setActive(true);
			roleService.insert(role);
		} else if (!role.isActive()) {
			role.setActive(true);
			roleService.update(role);
		}

		if (module.getId() == null) {
			moduleService.insert(module);
		}

		RoleModule roleModule = roleModuleService.getRoleModuleByRoleAndModuleId(role.getId(), module.getId());

		if (roleModule.getId() == null) {
			roleModule.setRole(role);
			roleModule.setSystemModule(module);
			roleModule.setSysUserId("1");
			roleModule.setHasAdd("Y");
			roleModule.setHasDelete("Y");
			roleModule.setHasSelect("Y");
			roleModule.setHasUpdate("Y");
			roleModuleService.insert(roleModule);
		}

		return true;
	}
}
