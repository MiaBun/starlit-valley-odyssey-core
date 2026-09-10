package com.CuteNekoDragon.Core.common.svogt.machine.singleblock.artisan;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;

public class MailBox extends MetaMachine {


    public MailBox(IMachineBlockEntity holder) {
        super(holder);
    }

    /*
    TODO: implement the actual block, implementation details of what's required/needed:

      right click to open a GUI
      in the GUI you can read "mail" send by npc's you placed
      gui:
      left side list of letters you received, right side the contents of the letter,
      small settings menu you can use to enable/disable toast popups for you've got mail

      more details:
      - new letters can be made with a md
      - particle if you have unread mail
      - special render to display mail inside of the mailbox
      - letters you as a player have received and read are stored on the player
     */
}
