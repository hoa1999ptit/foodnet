/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui.utils;

import foodnet.foodnetclient.entities.AuthEntity;

/**
 *
 * @author Kushtrim Hajrizi
 */
public abstract class ControllerUtils {
    public static boolean hasAdminPrefix(AuthEntity ae) {
        return ae.getUsername().startsWith("fn_");
    }
}
