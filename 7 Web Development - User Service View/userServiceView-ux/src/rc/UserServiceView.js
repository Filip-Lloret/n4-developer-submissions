/** @jsx spandrel.jsx */

define([ 'bajaux/spandrel' ], function (spandrel) {

    'use strict';
    // based on docDeveloper->SpandrelExamples->ChildWidgets
    const UserCard = spandrel((user) => [
    <div class={`user-card ${user.getEnabled() ? "enabled" : "disabled"}`}
    onClick={() => niagara.env.hyperlink(`station:|slot:${user.toPathString()}`)} // if the user service is named something else
    >
      <p class="user-initials">{user.initials}</p>
      <div class="user-name">
        <p class="primary-name">{ user.hasFullName ? user.getFullName() : user.getName()}</p>
        {user.hasFullName &&
        <p class="secondary-name">{user.getName()}</p>
        }
      </div>
      <p class="user-roles">
       {user.roles.map((role, index) => (
          <span className="role-badge" >{role}</span>
        ))}
      </p>
    </div>
    ]);

    const PropertyListWidget = spandrel((comp) => (
      <div class="user-service-view">
        <div class="user-grid">
        {
          comp.getSlots().properties().toArray().filter(p=>p.getType().is("baja:User")).map((prop) => {
             let userComponent = comp.get(prop.getName());
             userComponent.lease()
             let name = userComponent.getName();
             let fullName = userComponent.getFullName();
             let initials = "";
             let hasFullName = false;

             if(fullName == "") {
               initials = name.substring(0,2);
             } else {
               initials = fullName.split(" ").map(ele => ele.substring(0,1)).join("").toUpperCase();
               hasFullName = true;
             }

             console.log(userComponent.getRoles());
             userComponent.initials = initials;
             userComponent.hasFullName = hasFullName;
             userComponent.roles = userComponent.getRoles().split(",");

            return <UserCard tagName="div"  value={ userComponent } />;
          })
        }
        </div>
      </div>
    ));

    return PropertyListWidget;
  });
