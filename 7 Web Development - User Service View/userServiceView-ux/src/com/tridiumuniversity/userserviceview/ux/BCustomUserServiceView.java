package com.tridiumuniversity.userserviceview.ux;

import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.AgentOn;
import javax.baja.nre.annotations.NiagaraSingleton;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BSingleton;
import javax.baja.sys.Context;
import javax.baja.web.BIFormFactorMax;
import javax.baja.web.js.BIJavaScript;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.web.js.JsInfo;

@NiagaraType(agent = @AgentOn(types = "baja:UserService"))
@NiagaraSingleton
public class BCustomUserServiceView  extends BSingleton implements BIJavaScript, BIFormFactorMax {
//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tridiumuniversity.userserviceview.ux.BCustomUserServiceView(3329009296)1.0$ @*/
/* Generated Wed Apr 30 17:16:43 BST 2025 by Slot-o-Matic (c) Tridium, Inc. 2012-2025 */

  public static final BCustomUserServiceView INSTANCE = new BCustomUserServiceView();

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BCustomUserServiceView.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public JsInfo getJsInfo(Context cx) { return jsInfo; }
    private static final JsInfo jsInfo =
            JsInfo.make(
                    BOrd.make("module://userServiceView/rc/UserServiceView.js"),
                    BUserServiceViewJsBuild.TYPE
            );

}
