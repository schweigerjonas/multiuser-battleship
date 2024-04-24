import fenster.*;

class SVView extends Fenster{
    SVClient client;
    int id; 
    Knopf[] ks1 = new Knopf[100];
    Knopf[] ks2 = new Knopf[100];
    int[] fs1 = new int[100];
    int[] fs2 = new int[100];
    Bild neustartIcon = new Bild("img/Schiffe_versenken-Neustart-Icon.jpeg", 32, 32);
    Bildknopf neustart = new Bildknopf(neustartIcon, 32, 32);
    Bild phasenwechselIcon = new Bild("img/Schiffe_versenken-Phasenwechsel-Icon.jpeg", 32, 32);
    Bildknopf phasenwechsel = new Bildknopf(phasenwechselIcon, 32, 32);
    Auswahl schiffsposition = new Auswahl(new String[]{"Horizontal","Vertikal"}, 90, 20);
    Auswahl schiffswahl = new Auswahl(new String[]{"U-Boot","Zerstörer","Kreuzer"}, 90, 20);
    Bild ueberschrift = new Bild("img/Schiffe_versenken-Ueberschrift-Icon.jpeg", 430, 80);
    Bild schlachtfeld = new Bild("img/Schiffe_versenken-Schlachtfeld-Schriftzug.jpeg", 190, 26);
    Bild platzierfeld = new Bild("img/Schiffe_versenken-Platzierfeld-Schriftzug.jpeg", 186, 26);
    Bild rasterS = new Bild("img/Schiffe_versenken-Raster.jpeg", 275, 275);
    Bild rasterP = new Bild("img/Schiffe_versenken-Raster.jpeg", 275, 275);
    Bild spieler1Zustand = new Bild("img/Schiffe_versenken-Spieler1-Schriftzug.jpeg", 128, 26);
    Bild spieler2Zustand = new Bild("img/Schiffe_versenken-Spieler2-Schriftzug.jpeg", 132, 26);
    Bild spielzustandP = new Bild("img/Schiffe_versenken-Spielzustand-Platzieren-Schriftzug.jpeg", 370, 26);
    Bild spielzustandZ = new Bild("img/Schiffe_versenken-Spielzustand-Zug-Schriftzug.jpeg", 283, 26);
    Bild spielzustandS = new Bild("img/Schiffe_versenken-Spielzustand-Sieg-Schriftzug.jpeg", 295, 26);

    SVView(SVClient client){
        this.client = client;
        this.groesseSetzen(651, 580);
        this.farbeSetzen(207, 207, 207);
        
        this.hinzufuegen(ueberschrift, 111, 5);

        neustart.groesseSetzen(32, 32);
        this.hinzufuegen(neustart, 302, 182);
        neustart.kommentarHinzufuegen("Starte ein neues Spiel.");

        phasenwechsel.groesseSetzen(32, 32);
        this.hinzufuegen(phasenwechsel, 302, 244);
        phasenwechsel.kommentarHinzufuegen("Wechsle in die Zugphase.");
        phasenwechsel.deaktivieren();

        this.hinzufuegen(schlachtfeld, 5, 115);
        this.hinzufuegen(platzierfeld, 356, 115);

        this.hinzufuegen(schiffswahl, 405, 450);
        schiffswahl.deaktivieren();

        this.hinzufuegen(schiffsposition, 517, 450);
        schiffsposition.deaktivieren();

        this.hinzufuegen(rasterS, 5, 150);
        this.hinzufuegen(rasterP, 356, 150);
    }

    public void receiveMessage(String text){
        String[] s = text.split(":"); 
        if(s[0].equals("id")){
            id = client.toInt(s[1]);
            return;     
        }

        if(s[0].equals("start")){
            neustart.deaktivieren();
            if(id == 1){         //erst hier erzeugt, da davor id noch nicht feststeht
                for(int i=0; i<100; i++){
                    ks1[i] = new Knopf("");
                    ks1[i].groesseSetzen(25, 25);
                    ks1[i].farbeSetzen(119, 136, 153);
                    this.hinzufuegen(ks1[i], 30 + 25 * (i%10), 175 + 25 * (i/10));
                    ks1[i].deaktivieren();          //Schlachtfeld Spieler1

                    ks2[i] = new Knopf("");
                    ks2[i].groesseSetzen(25, 25);
                    ks2[i].farbeSetzen(119, 136, 153);
                    this.hinzufuegen(ks2[i], 381 + 25 * (i%10), 175 + 25 * (i/10));
                    ks2[i].aktivieren();            //Platzierfeld Spieler1
                }
                Bild spieler1 = new Bild("img/Schiffe_versenken-Spieler1-Schriftzug.jpeg", 128, 26);
                this.hinzufuegen(spieler1, 5, 445);

                this.hinzufuegen(spielzustandP, 1, 480);
            }else{
                for(int i=0; i<100; i++){ 
                    ks2[i] = new Knopf("");
                    ks2[i].groesseSetzen(25, 25);
                    ks2[i].farbeSetzen(119, 136, 153);
                    this.hinzufuegen(ks2[i], 30 + 25 * (i%10), 175 + 25 * (i/10));
                    ks2[i].deaktivieren();          //Schlachtfeld Spieler2

                    ks1[i] = new Knopf("");
                    ks1[i].groesseSetzen(25, 25);
                    ks1[i].farbeSetzen(119, 136, 153);
                    this.hinzufuegen(ks1[i], 381 + 25 * (i%10), 175 + 25 * (i/10));
                    ks1[i].aktivieren();            //Platzierfeld Spieler2
                } 
                Bild spieler2 = new Bild("img/Schiffe_versenken-Spieler2-Schriftzug.jpeg", 132, 26);
                this.hinzufuegen(spieler2, 5, 445);

                this.hinzufuegen(spielzustandP, 1, 480);
            }
            this.titelSetzen("Spieler "+ id);
            for(int i=0; i<100; i++){
                fs1[i] = 0;
                fs2[i] = 0;              
            }
            schiffswahl.aktivieren();
            schiffsposition.aktivieren();
            return;
        }

        if(s[0].equals("platzieren")){
            if(s[1].equals("2")){
                for(int i=0; i<100; i++){
                    fs1[i] = client.toInt(s[3 + i]);              
                    ks1[i].deaktivieren();                  
                    ks1[i].farbeSetzen(119, 136, 153); 
                    fs2[i] = client.toInt(s[103 + i]);                        
                    ks2[i].deaktivieren();
                    ks2[i].farbeSetzen(119, 136, 153);
                }
                phasenwechsel.aktivieren();
                this.entfernen(spielzustandP);
                this.remove(schiffsposition);
                this.remove(schiffswahl);
            }else{
                if(id == 1){
                    for(int i=0; i<100; i++){          
                        fs2[i] = client.toInt(s[3 + i]);
                        aktualisiereAktivierungPlatzierung1();
                    }
                    return;
                }else{
                    for(int i=0; i<100; i++){
                        fs1[i] = client.toInt(s[3 + i]);
                        aktualisiereAktivierungPlatzierung2();
                    }
                    return;
                }    
            }
        }

        if(s[0].equals("phasenwechsel")){
            int zustand = client.toInt(s[2]);
            phasenwechsel.deaktivieren();
            this.hinzufuegen(spielzustandZ, 1, 480);
            if(id == 1){
                aktualisiereAktivierungPlatzierfeld1();    
            }else{
                aktualisiereAktivierungPlatzierfeld2();    
            }
            if(zustand == id){
                if(zustand == 1){
                    for(int i=0; i<100; i++){
                        ks1[i].aktivieren();
                    }
                }else{
                    for(int i=0; i<100; i++){
                        ks2[i].aktivieren();
                    }
                }
            }
            if(zustand == 1){
                this.hinzufuegen(spieler1Zustand, 285, 480);
            }else{
                this.hinzufuegen(spieler2Zustand, 285, 480);    
            }
            return;
        }

        if(s[0].equals("zug")){
            int zw = client.toInt(s[2]);
            for(int i=0; i<100; i++){
                fs1[i] = client.toInt(s[3 + i]);
                aktualisiereAktivierungZug1();
            }
            for(int i=0; i<100; i++){
                fs2[i] = client.toInt(s[103 + i]);
                aktualisiereAktivierungZug2();
            }

            if(zw == 2){
                if(s[1].equals("1")){
                    for(int i=0; i<100; i++){
                        ks1[i].deaktivieren();
                        if(id == 2){
                            ks2[i].aktivieren();
                            aktualisiereAktivierungZug2();
                        }
                    }
                    this.entfernen(spieler1Zustand);
                    this.hinzufuegen(spieler2Zustand, 285, 480);
                }else{
                    if(s[1].equals("2")){
                        for(int i=0; i<100; i++){
                            ks2[i].deaktivieren();
                            if(id == 1){
                                ks1[i].aktivieren();
                                aktualisiereAktivierungZug1();
                            }
                        }    
                    }
                    this.entfernen(spieler2Zustand);
                    this.hinzufuegen(spieler1Zustand, 285, 480);
                }
            }
            return;
        }

        if(s[0].equals("ende")){
            int id = client.toInt(s[1]);
            aktualisiereAktivierungZug1();
            aktualisiereAktivierungZug2();
            for(int i=0; i<100; i++){
                ks1[i].deaktivieren();
                ks2[i].deaktivieren();
            }
            this.entfernen(spielzustandZ);
            this.hinzufuegen(spielzustandS, 1, 480);
            if(id == 1){
                this.hinzufuegen(spieler1Zustand, 295, 480);    
            }else{
                this.hinzufuegen(spieler2Zustand, 295, 480);
            }
            neustart.aktivieren();
            return;
        }

        if(s[0].equals("neustart")){
            neustart.deaktivieren();
            this.hinzufuegen(spieler1Zustand, 50, 230);
            this.entfernen(spieler1Zustand);
            this.hinzufuegen(spieler2Zustand, 50, 230);
            this.entfernen(spieler2Zustand);
            this.entfernen(spielzustandS);
            if(id == 1){ 
                for(int i=0; i<100; i++){
                    ks1[i].farbeSetzen(119, 136, 153);
                    ks1[i].deaktivieren();
                    ks2[i].farbeSetzen(119, 136, 153);
                    ks2[i].aktivieren();   
                }
            }else{
                for(int i=0; i<100; i++){ 
                    ks2[i].farbeSetzen(119, 136, 153);
                    ks2[i].deaktivieren(); 
                    ks1[i].farbeSetzen(119, 136, 153);
                    ks1[i].aktivieren();  
                }
            }
            this.titelSetzen("Spieler "+ id);
            for(int i=0; i<100; i++){
                fs1[i] = 0;
                fs2[i] = 0;              
            }
            this.hinzufuegen(schiffswahl, 405, 450);
            schiffswahl.aktivieren();
            this.hinzufuegen(schiffsposition, 517, 450);
            schiffsposition.aktivieren();
            this.hinzufuegen(spielzustandP, 1, 480);
            return;    
        }
    }

    @Override
    public void aktion(Object o){
        String sw = (String)(schiffswahl.getSelectedItem());                   
        String sp = (String)(schiffsposition.getSelectedItem());                        
        if(o == neustart){
            client.sendMessage("neustart:"+id);
            return;
        }
        if(o == phasenwechsel){
            client.sendMessage("phasenwechsel:"+id);
            return;
        }
        if(id == 1){
            for(int i=0; i<100; i++){
                if(o == ks1[i]){ 
                    client.sendMessage("zug:"+id+":"+i);
                }
                if(o == ks2[i]){
                    client.sendMessage("platzieren:"+id+":"+sw+":"+sp+":"+i);            
                }
            }
            return;
        }else{
            for(int i=0; i<100; i++){
                if(o == ks2[i]){ 
                    client.sendMessage("zug:"+id+":"+i);
                }
                if(o == ks1[i]){
                    client.sendMessage("platzieren:"+id+":"+sw+":"+sp+":"+i);         
                }
            }  
            return;
        }
    }

    public void aktualisiereAktivierungPlatzierung1(){
        for(int i=0; i<100; i++){
            if(fs2[i] == -1){
                ks2[i].farbeSetzen(65,105,225);
                ks2[i].deaktivieren();
            }
            if(fs2[i] == 0){
                ks2[i].farbeSetzen(119, 136, 153);
                ks2[i].aktivieren();
            }
            if(fs2[i] == 1){
                ks2[i].farbeSetzen(72, 61, 139);
                ks2[i].deaktivieren();
            }
            if(fs2[i] == 2){
                ks2[i].farbeSetzen(72, 61, 139);
                ks2[i].deaktivieren();    
            }
            if(fs2[i] == 3){
                ks2[i].farbeSetzen(72, 61, 139);
                ks2[i].deaktivieren();
            }        
        }
    }

    public void aktualisiereAktivierungPlatzierung2(){
        for(int i=0; i<100; i++){
            if(fs1[i] == -1){
                ks1[i].farbeSetzen(65, 105, 225);
                ks1[i].deaktivieren();
            }
            if(fs1[i] == 0){
                ks1[i].farbeSetzen(119, 136, 153);
                ks1[i].aktivieren();
            }
            if(fs1[i] == 1){
                ks1[i].farbeSetzen(72, 61, 139);
                ks1[i].deaktivieren();
            }
            if(fs1[i] == 2){
                ks1[i].farbeSetzen(72, 61, 139);
                ks1[i].deaktivieren();    
            }
            if(fs1[i] == 3){
                ks1[i].farbeSetzen(72, 61, 139);
                ks1[i].deaktivieren();
            }        
        }
    }

    public void aktualisiereAktivierungPlatzierfeld1(){
        for(int i=0; i<100; i++){
            if(fs2[i] == -1){
                ks2[i].farbeSetzen(119, 136, 153);
                ks2[i].deaktivieren();
            }
            if(fs2[i] == 0){
                ks2[i].farbeSetzen(119, 136, 153);
                ks2[i].deaktivieren();
            }
            if(fs2[i] == 1){
                ks2[i].farbeSetzen(72, 61, 139);
                ks2[i].deaktivieren();
            }
            if(fs2[i] == 2){
                ks2[i].farbeSetzen(72, 61, 139);
                ks2[i].deaktivieren();    
            }
            if(fs2[i] == 3){
                ks2[i].farbeSetzen(72, 61, 139);
                ks2[i].deaktivieren();
            }        
        }
    }

    public void aktualisiereAktivierungPlatzierfeld2(){
        for(int i=0; i<100; i++){
            if(fs1[i] == -1){
                ks1[i].farbeSetzen(119, 136, 153);
                ks1[i].deaktivieren();
            }
            if(fs1[i] == 0){
                ks1[i].farbeSetzen(119, 136, 153);
                ks1[i].deaktivieren();
            }
            if(fs1[i] == 1){
                ks1[i].farbeSetzen(72, 61, 139);
                ks1[i].deaktivieren();
            }
            if(fs1[i] == 2){
                ks1[i].farbeSetzen(72, 61, 139);
                ks1[i].deaktivieren();    
            }
            if(fs1[i] == 3){
                ks1[i].farbeSetzen(72, 61, 139);
                ks1[i].deaktivieren();
            }        
        }
    }

    public void aktualisiereAktivierungZug1(){
        for(int i=0; i<100; i++){
            if(fs1[i] == 4){
                ks1[i].farbeSetzen(30, 144, 255);
                ks1[i].deaktivieren();
            }
            if(fs1[i] == 11){
                ks1[i].farbeSetzen("schwarz");
                ks1[i].deaktivieren();
            }
            if(fs1[i] == 22){
                ks1[i].farbeSetzen("rot");
                ks1[i].deaktivieren();
            }
            if(fs1[i] == 33){
                ks1[i].farbeSetzen("rot");
                ks1[i].deaktivieren();
            }
            if(fs1[i] == 222){
                ks1[i].farbeSetzen("schwarz");
                ks1[i].deaktivieren();
            }
            if(fs1[i] == 333){
                ks1[i].farbeSetzen("schwarz");
                ks1[i].deaktivieren();
            }
        }
    }

    public void aktualisiereAktivierungZug2(){
        for(int i=0; i<100; i++){
            if(fs2[i] == 4){
                ks2[i].farbeSetzen(30, 144, 255);
                ks2[i].deaktivieren();
            }
            if(fs2[i] == 11){
                ks2[i].farbeSetzen("schwarz");
                ks2[i].deaktivieren();
            }
            if(fs2[i] == 22){
                ks2[i].farbeSetzen("rot");
                ks2[i].deaktivieren();
            }
            if(fs2[i] == 33){
                ks2[i].farbeSetzen("rot");
                ks2[i].deaktivieren();
            }
            if(fs2[i] == 222){
                ks2[i].farbeSetzen("schwarz");
                ks2[i].deaktivieren();
            }
            if(fs2[i] == 333){
                ks2[i].farbeSetzen("schwarz");
                ks2[i].deaktivieren();
            }
        }
    }
}
