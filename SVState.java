import server.*;
import java.util.Random;

class SVState{
    SVServer server;
    int[] fs1 = new int[100];
    int[] fs2 = new int[100];
    int zustand = 0;
    int idcount = 0;
    int starter = 0;
    int UCount1 = 0;
    int UCount2 = 0;
    int ZCount1 = 0;
    int ZCount2 = 0;
    int KCount1 = 0;
    int KCount2 = 0;
    int sZerstört1 = 0;
    int sZerstört2 = 0;
    int umax = 4;
    int zmax = 3;
    int kmax = 2;
    int smax = 9;
    Random r = new Random();

    SVState(SVServer server){
        this.server = server;        
    }

    void start(){
        starter = r.nextInt(2)+1;
        zustand = starter;
        server.sendAll("start:"+zustand);
    }

    void neustart(){
        fs1 = new int[100];
        fs2 = new int[100];
        UCount1 = 0;
        UCount2 = 0;
        ZCount1 = 0;
        ZCount2 = 0;
        KCount1 = 0;
        KCount2 = 0;
        sZerstört1 = 0;
        sZerstört2 = 0;
        starter = r.nextInt(2)+1;
        zustand = starter;
        server.sendAll("neustart:"+zustand);
    }

    void receiveMessage(String message, SClient client){
        String[] s = message.split(":");
        if(s[0].equals("neu")){
            server.sendMessage("id:" + (idcount % 2 + 1), client);
            idcount++;
            if(server.aktNumberOfClients() == 2){
                start();    
            }
            return;
        }

        if(s[0].equals("start")){
            start();    
        }

        if(s[0].equals("platzieren")){
            int id = server.toInt(s[1]);
            int uegk = server.toInt(s[4]);
            int zeile = uegk/10;
            int spalte = uegk%10;
            if(id == 1){
                if(s[2].equals("U-Boot") && fs2[uegk] == 0 && UCount1 < umax){
                    fs2[uegk] = 1;
                    UCount1++;
                    if(zeile > 0){
                        fs2[uegk - 10] = -1;
                        if(spalte > 0){
                            fs2[uegk - 1] = -1;
                            fs2[uegk - 11] = -1;
                        }
                        if (spalte < 9){
                            fs2[uegk + 1] = -1;
                            fs2[uegk - 9] = -1;
                        }
                    }
                    if(zeile < 9){
                        fs2[uegk + 10] = -1;
                        if(spalte > 0){
                            fs2[uegk - 1] = -1;
                            fs2[uegk + 9] = -1;
                        }
                        if (spalte < 9){
                            fs2[uegk + 1] = -1;
                            fs2[uegk + 11] = -1;
                        }
                    }
                }
                if(s[2].equals("Zerstörer") && s[3].equals("Vertikal") && ZCount1 < zmax){
                    if(fs2[uegk] == 0 && zeile < 9 && fs2[uegk + 10] == 0){
                        fs2[uegk] = 2;
                        fs2[uegk + 10] = 2;
                        ZCount1++;
                        if(zeile > 0){
                            fs2[uegk-10] = -1;
                            if(spalte > 0){
                                fs2[uegk-11] = -1;
                                fs2[uegk-1] = -1;
                                fs2[uegk+9] = -1;
                            }
                            if (spalte < 9){
                                fs2[uegk-9] = -1;
                                fs2[uegk+1] = -1;
                                fs2[uegk+11] = -1;
                            }
                        }
                        if(zeile < 8){
                            fs2[uegk+20] = -1;
                            if(spalte > 0){
                                fs2[uegk-1] = -1;
                                fs2[uegk+9] = -1;
                                fs2[uegk+19] = -1;
                            }
                            if (spalte < 9){
                                fs2[uegk+1] = -1;
                                fs2[uegk+11] = -1;
                                fs2[uegk+21] = -1;
                            }
                        }
                    }
                }
                if(s[2].equals("Zerstörer") && s[3].equals("Horizontal") && ZCount1 < zmax){
                    if(fs2[uegk] == 0 && spalte < 9 &&  fs2[uegk + 1] == 0){
                        fs2[uegk] = 2;
                        fs2[uegk + 1] = 2; 
                        ZCount1++;
                        if(zeile > 0){
                            fs2[uegk-10] = -1;
                            fs2[uegk-9] = -1;
                            if(spalte > 0){
                                fs2[uegk-1] = -1;
                                fs2[uegk-11] = -1;
                            }
                            if (spalte < 8){
                                fs2[uegk+2] = -1;
                                fs2[uegk-8] = -1;
                            }
                        }
                        if(zeile < 9){
                            fs2[uegk+10] = -1;
                            fs2[uegk+11] = -1;
                            if(spalte > 0){
                                fs2[uegk-1] = -1;
                                fs2[uegk+9] = -1;
                            }
                            if (spalte < 8){
                                fs2[uegk+2] = -1;
                                fs2[uegk+12] = -1;
                            }
                        }
                    }
                }
                if(s[2].equals("Kreuzer") && s[3].equals("Vertikal") && KCount1 < kmax){
                    if(fs2[uegk] == 0 && zeile < 8 && fs2[uegk + 10] == 0 && fs2[uegk + 20] == 0){
                        fs2[uegk] = 3;
                        fs2[uegk + 10] = 3;
                        fs2[uegk + 20] = 3;
                        KCount1++;
                        if(zeile > 0){
                            fs2[uegk-10] = -1;
                            if(spalte > 0){
                                fs2[uegk-11] = -1;
                                fs2[uegk-1] = -1;
                                fs2[uegk+9] = -1;
                                fs2[uegk+19] = -1;
                            }
                            if(spalte < 9){
                                fs2[uegk-9] = -1;
                                fs2[uegk+1] = -1;
                                fs2[uegk+11] = -1;
                                fs2[uegk+21] = -1;
                            }
                        }
                        if(zeile < 7){
                            fs2[uegk+30] = -1;
                            if(spalte > 0){
                                fs2[uegk-1] = -1;
                                fs2[uegk+9] = -1;
                                fs2[uegk+19] = -1;
                                fs2[uegk+29] = -1;
                            }
                            if(spalte < 9){
                                fs2[uegk+1] = -1;
                                fs2[uegk+11] = -1;
                                fs2[uegk+21] = -1;
                                fs2[uegk+31] = -1;
                            }
                        }
                    }
                }
                if(s[2].equals("Kreuzer") && s[3].equals("Horizontal") && KCount1 < kmax){
                    if(fs2[uegk] == 0 && spalte < 8 && fs2[uegk+1] == 0 && fs2[uegk+2] == 0){
                        fs2[uegk] = 3;
                        fs2[uegk+1] = 3;
                        fs2[uegk+2] = 3;
                        KCount1++;
                        if(zeile > 0){
                            fs2[uegk-10] = -1;
                            fs2[uegk-9] = -1;
                            fs2[uegk-8] = -1;
                            if(spalte > 0){
                                fs2[uegk-1] = -1;
                                fs2[uegk-11] = -1;
                            }
                            if(spalte < 7){
                                fs2[uegk+3] = -1;
                                fs2[uegk-7] = -1;        
                            }
                        }
                        if(zeile < 9){
                            fs2[uegk+10] = -1;
                            fs2[uegk+11] = -1;
                            fs2[uegk+12] = -1;
                            if(spalte > 0){
                                fs2[uegk-1] = -1;
                                fs2[uegk+9] = -1;
                            }
                            if (spalte < 7){
                                fs2[uegk+3] = -1;
                                fs2[uegk+13] = -1;
                            }
                        }
                    }
                }
            }else{
                if(s[2].equals("U-Boot") && fs1[uegk] == 0 && UCount2 < umax){
                    fs1[uegk] = 1;
                    UCount2++;
                    if(zeile > 0){
                        fs1[uegk - 10] = -1;
                        if(spalte > 0){
                            fs1[uegk - 1] = -1;
                            fs1[uegk - 11] = -1;
                        }
                        if (spalte < 9){
                            fs1[uegk + 1] = -1;
                            fs1[uegk - 9] = -1;
                        }
                    }
                    if(zeile < 9){
                        fs1[uegk + 10] = -1;
                        if(spalte > 0){
                            fs1[uegk - 1] = -1;
                            fs1[uegk + 9] = -1;
                        }
                        if (spalte < 9){
                            fs1[uegk + 1] = -1;
                            fs1[uegk + 11] = -1;
                        }
                    }
                } 
                if(s[2].equals("Zerstörer") && s[3].equals("Vertikal") && ZCount2 < zmax){
                    if(fs1[uegk] == 0 && zeile < 9 && fs1[uegk + 10] == 0){
                        fs1[uegk] = 2;
                        fs1[uegk + 10] = 2;
                        ZCount2++;
                        if(zeile > 0){
                            fs1[uegk-10] = -1;
                            if(spalte > 0){
                                fs1[uegk-11] = -1;
                                fs1[uegk-1] = -1;
                                fs1[uegk+9] = -1;
                            }
                            if (spalte < 9){
                                fs1[uegk-9] = -1;
                                fs1[uegk+1] = -1;
                                fs1[uegk+11] = -1;
                            }
                        }
                        if(zeile < 8){
                            fs1[uegk+20] = -1;
                            if(spalte > 0){
                                fs1[uegk-1] = -1;
                                fs1[uegk+9] = -1;
                                fs1[uegk+19] = -1;
                            }
                            if (spalte < 9){
                                fs1[uegk+1] = -1;
                                fs1[uegk+11] = -1;
                                fs1[uegk+21] = -1;
                            }
                        }
                    }
                }
                if(s[2].equals("Zerstörer") && s[3].equals("Horizontal") && ZCount2 < zmax){
                    if(fs1[uegk] == 0 && spalte < 9 &&  fs1[uegk + 1] == 0){
                        fs1[uegk] = 2;
                        fs1[uegk + 1] = 2; 
                        ZCount2++;
                        if(zeile > 0){
                            fs1[uegk-10] = -1;
                            fs1[uegk-9] = -1;
                            if(spalte > 0){
                                fs1[uegk-1] = -1;
                                fs1[uegk-11] = -1;
                            }
                            if (spalte < 8){
                                fs1[uegk+2] = -1;
                                fs1[uegk-8] = -1;
                            }
                        }
                        if(zeile < 9){
                            fs1[uegk+10] = -1;
                            fs1[uegk+11] = -1;
                            if(spalte > 0){
                                fs1[uegk-1] = -1;
                                fs1[uegk+9] = -1;
                            }
                            if (spalte < 8){
                                fs1[uegk+2] = -1;
                                fs1[uegk+12] = -1;
                            }
                        }
                    }
                }
                if(s[2].equals("Kreuzer") && s[3].equals("Vertikal") && KCount2 < kmax){
                    if(fs1[uegk] == 0 && zeile < 8 && fs1[uegk + 10] == 0 && fs1[uegk + 20] == 0){
                        fs1[uegk] = 3;
                        fs1[uegk + 10] = 3;
                        fs1[uegk + 20] = 3;
                        KCount2++;
                        if(zeile > 0){
                            fs1[uegk-10] = -1;
                            if(spalte > 0){
                                fs1[uegk-11] = -1;
                                fs1[uegk-1] = -1;
                                fs1[uegk+9] = -1;
                                fs1[uegk+19] = -1;
                            }
                            if(spalte < 9){
                                fs1[uegk-9] = -1;
                                fs1[uegk+1] = -1;
                                fs1[uegk+11] = -1;
                                fs1[uegk+21] = -1;
                            }
                        }
                        if(zeile < 7){
                            fs1[uegk+30] = -1;
                            if(spalte > 0){
                                fs1[uegk-1] = -1;
                                fs1[uegk+9] = -1;
                                fs1[uegk+19] = -1;
                                fs1[uegk+29] = -1;
                            }
                            if(spalte < 9){
                                fs1[uegk+1] = -1;
                                fs1[uegk+11] = -1;
                                fs1[uegk+21] = -1;
                                fs1[uegk+31] = -1;
                            }
                        }
                    }
                }
                if(s[2].equals("Kreuzer") && s[3].equals("Horizontal") && KCount2 < kmax){
                    if(fs1[uegk] == 0 && spalte < 8 && fs1[uegk+1] == 0 && fs1[uegk+2] == 0){
                        fs1[uegk] = 3;
                        fs1[uegk+1] = 3;
                        fs1[uegk+2] = 3;
                        KCount2++;
                        if(zeile > 0){
                            fs1[uegk-10] = -1;
                            fs1[uegk-9] = -1;
                            fs1[uegk-8] = -1;
                            if(spalte > 0){
                                fs1[uegk-1] = -1;
                                fs1[uegk-11] = -1;
                            }
                            if(spalte < 7){
                                fs1[uegk+3] = -1;
                                fs1[uegk-7] = -1;        
                            }
                        }
                        if(zeile < 9){
                            fs1[uegk+10] = -1;
                            fs1[uegk+11] = -1;
                            fs1[uegk+12] = -1;
                            if(spalte > 0){
                                fs1[uegk-1] = -1;
                                fs1[uegk+9] = -1;
                            }
                            if (spalte < 7){
                                fs1[uegk+3] = -1;
                                fs1[uegk+13] = -1;
                            }
                        }
                    }
                }
            }
            String fb1 = "";            
            String fb2 = "";            
            int pe = 1;                 
            for(int i=0; i<100; i++){
                fb1 = fb1+":"+fs1[i];
                fb2 = fb2+":"+fs2[i];
            }
            if(id == 1){
                server.sendMessage("platzieren:"+pe+":"+id+fb2, client);
            }else{
                server.sendMessage("platzieren:"+pe+":"+id+fb1, client);    
            }

            if(UCount1==umax && UCount2==umax && ZCount1==zmax && ZCount2==zmax && KCount1==kmax && KCount2==kmax){
                pe = 2;
                server.sendAll("platzieren:"+pe+":"+id+fb1+fb2);    
            }
            return;
        }

        if(s[0].equals("phasenwechsel")){
            int id = server.toInt(s[1]);
            server.sendAll("phasenwechsel:"+id+":"+zustand);
        }

        if(s[0].equals("zug")){
            int id = server.toInt(s[1]);
            int uegk = server.toInt(s[2]); 
            int zw = 1;
            if(id == 1){
                if(fs1[uegk] == -1 || fs1[uegk] == 0){
                    fs1[uegk] = 4;  
                    zw = 2;
                }else{
                    sZerstört1++;
                    if(fs1[uegk] == 1){
                        fs1[uegk] = 11;    
                    }else{
                        if(fs1[uegk] == 2){
                            fs1[uegk] = 22;
                            umkreisPruefenZerstörer1(uegk);
                        }else{
                            if(fs1[uegk] == 3){
                                fs1[uegk] = 33;
                                umkreisPruefenKreuzer1(uegk);
                            }
                        }
                    }
                }
            }else{
                if(fs2[uegk] == -1 || fs2[uegk] == 0){
                    fs2[uegk] = 4;
                    zw = 2;
                }else{
                    sZerstört2++;
                    if(fs2[uegk] == 1){
                        fs2[uegk] = 11;    
                    }else{
                        if(fs2[uegk] == 2){
                            fs2[uegk] = 22;
                            umkreisPruefenZerstörer2(uegk);
                        }else{
                            if(fs2[uegk] == 3){
                                fs2[uegk] = 33;
                                umkreisPruefenKreuzer2(uegk);
                            }
                        }
                    }
                }
            }
            String fb1 = "";
            String fb2 = "";
            for(int i=0; i<100; i++){
                fb1 = fb1+":"+fs1[i];
                fb2 = fb2+":"+fs2[i];
            }
            server.sendAll("zug:"+id+":"+zw+fb1+fb2); 
            if(sZerstört1 == 16 || sZerstört2 == 16){
                server.sendAll("ende:"+id);
            }
            return;
        } 

        if(s[0].equals("neustart")){
            neustart();
        }
    }

    public void umkreisPruefenZerstörer1(int uegk){
        int zeile = uegk/10;
        int spalte = uegk%10;     

        if(fs1[uegk] == 22){
            if(zeile > 0){
                if(fs1[uegk-10] == 22){
                    fs1[uegk-10] = 222; 
                    fs1[uegk] = 222;
                }
                if(spalte > 0){
                    if(fs1[uegk-1] == 22){
                        fs1[uegk-1] = 222;
                        fs1[uegk] = 222;
                    }
                }else{
                    if(spalte == 0){
                        if(fs1[uegk+1] == 22){
                            fs1[uegk+1] = 222; 
                            fs1[uegk] = 222;
                        }          
                    }
                }
                if (spalte < 9){
                    if(fs1[uegk+1] == 22){
                        fs1[uegk+1] = 222;
                        fs1[uegk] = 222;
                    }
                }else{
                    if(spalte == 9){
                        if(fs1[uegk-1] == 22){
                            fs1[uegk-1] = 222;
                            fs1[uegk] = 222;
                        }            
                    }
                }
            }else{
                if(zeile == 0){
                    if(fs1[uegk+10] == 22){
                        fs1[uegk+10] = 222;
                        fs1[uegk] = 222;
                    }
                    if(spalte > 0){
                        if(fs1[uegk-1] == 22){
                            fs1[uegk-1] = 222; 
                            fs1[uegk] = 222;
                        }
                    }else{
                        if(spalte == 0){
                            if(fs1[uegk+1] == 22){
                                fs1[uegk+1] = 222; 
                                fs1[uegk] = 222;
                            }        
                        }
                    }
                    if(spalte < 9){
                        if(fs1[uegk+1] == 22){
                            fs1[uegk+1] = 222;
                            fs1[uegk] = 222;
                        }
                    }else{
                        if(spalte == 9){
                            if(fs1[uegk-1] == 22){
                                fs1[uegk-1] = 222; 
                                fs1[uegk] = 222;
                            }        
                        }
                    }
                }
            }
            if(zeile < 9){
                if(fs1[uegk+10] == 22){
                    fs1[uegk+10] = 222; 
                    fs1[uegk] = 222;
                }
                if(spalte > 0){
                    if(fs1[uegk-1] == 22){
                        fs1[uegk-1] = 222; 
                        fs1[uegk] = 222;
                    }
                }else{
                    if(spalte == 0){
                        if(fs1[uegk+1] == 22){
                            fs1[uegk+1] = 222; 
                            fs1[uegk] = 222;
                        }          
                    }
                }
                if (spalte < 9){
                    if(fs1[uegk+1] == 22){
                        fs1[uegk+1] = 222; 
                        fs1[uegk] = 222;
                    }
                }else{
                    if(spalte == 9){
                        if(fs1[uegk-1] == 22){
                            fs1[uegk-1] = 222; 
                            fs1[uegk] = 222;
                        }            
                    }
                }
            }else{
                if(zeile == 9){
                    if(fs1[uegk-10] == 22){
                        fs1[uegk-10] = 222;
                        fs1[uegk] = 222;
                    }
                    if(spalte > 0){
                        if(fs1[uegk-1] == 22){
                            fs1[uegk-1] = 222;
                            fs1[uegk] = 222;
                        }
                    }else{
                        if(spalte == 0){
                            if(fs1[uegk+1] == 22){
                                fs1[uegk+1] = 222;
                                fs1[uegk] = 222;
                            }        
                        }
                    }
                    if(spalte < 9){
                        if(fs1[uegk+1] == 22){
                            fs1[uegk+1] = 222;
                            fs1[uegk] = 222;
                        }
                    }else{
                        if(spalte == 9){
                            if(fs1[uegk-1] == 22){
                                fs1[uegk-1] = 222; 
                                fs1[uegk] = 222;
                            }        
                        }
                    }
                }
            }
        }
        return;
    }

    public void umkreisPruefenZerstörer2(int uegk){
        int zeile = uegk/10;
        int spalte = uegk%10;     

        if(fs2[uegk] == 22){
            if(zeile > 0){
                if(fs2[uegk-10] == 22){
                    fs2[uegk-10] = 222; 
                    fs2[uegk] = 222;
                }
                if(spalte > 0){
                    if(fs2[uegk-1] == 22){
                        fs2[uegk-1] = 222;
                        fs2[uegk] = 222;
                    }
                }else{
                    if(spalte == 0){
                        if(fs2[uegk+1] == 22){
                            fs2[uegk+1] = 222; 
                            fs2[uegk] = 222;
                        }          
                    }
                }
                if (spalte < 9){
                    if(fs2[uegk+1] == 22){
                        fs2[uegk+1] = 222;
                        fs2[uegk] = 222;
                    }
                }else{
                    if(spalte == 9){
                        if(fs2[uegk-1] == 22){
                            fs2[uegk-1] = 222;
                            fs2[uegk] = 222;
                        }            
                    }
                }
            }else{
                if(zeile == 0){
                    if(fs2[uegk+10] == 22){
                        fs2[uegk+10] = 222;
                        fs2[uegk] = 222;
                    }
                    if(spalte > 0){
                        if(fs2[uegk-1] == 22){
                            fs2[uegk-1] = 222; 
                            fs2[uegk] = 222;
                        }
                    }else{
                        if(spalte == 0){
                            if(fs2[uegk+1] == 22){
                                fs2[uegk+1] = 222; 
                                fs2[uegk] = 222;
                            }        
                        }
                    }
                    if(spalte < 9){
                        if(fs2[uegk+1] == 22){
                            fs2[uegk+1] = 222;
                            fs2[uegk] = 222;
                        }
                    }else{
                        if(spalte == 9){
                            if(fs2[uegk-1] == 22){
                                fs2[uegk-1] = 222; 
                                fs2[uegk] = 222;
                            }        
                        }
                    }
                }
            }
            if(zeile < 9){
                if(fs2[uegk+10] == 22){
                    fs2[uegk+10] = 222; 
                    fs2[uegk] = 222;
                }
                if(spalte > 0){
                    if(fs2[uegk-1] == 22){
                        fs2[uegk-1] = 222; 
                        fs2[uegk] = 222;
                    }
                }else{
                    if(spalte == 0){
                        if(fs2[uegk+1] == 22){
                            fs2[uegk+1] = 222; 
                            fs2[uegk] = 222;
                        }          
                    }
                }
                if (spalte < 9){
                    if(fs2[uegk+1] == 22){
                        fs2[uegk+1] = 222; 
                        fs2[uegk] = 222;
                    }
                }else{
                    if(spalte == 9){
                        if(fs2[uegk-1] == 22){
                            fs2[uegk-1] = 222; 
                            fs2[uegk] = 222;
                        }            
                    }
                }
            }else{
                if(zeile == 9){
                    if(fs2[uegk-10] == 22){
                        fs2[uegk-10] = 222;
                        fs2[uegk] = 222;
                    }
                    if(spalte > 0){
                        if(fs2[uegk-1] == 22){
                            fs2[uegk-1] = 222;
                            fs2[uegk] = 222;
                        }
                    }else{
                        if(spalte == 0){
                            if(fs2[uegk+1] == 22){
                                fs2[uegk+1] = 222;
                                fs2[uegk] = 222;
                            }        
                        }
                    }
                    if(spalte < 9){
                        if(fs2[uegk+1] == 22){
                            fs2[uegk+1] = 222;
                            fs2[uegk] = 222;
                        }
                    }else{
                        if(spalte == 9){
                            if(fs2[uegk-1] == 22){
                                fs2[uegk-1] = 222; 
                                fs2[uegk] = 222;
                            }        
                        }
                    }
                }
            }
        }
        return;
    }

    public void umkreisPruefenKreuzer1(int uegk){
        int zeile = uegk/10;
        int spalte = uegk%10;

        if(fs1[uegk] == 33){
            if(zeile > 0 && zeile < 9){
                if(zeile == 1){
                    if(uegk-10 >= 0 && fs1[uegk-10] == 33 && fs1[uegk+10] == 33){
                        fs1[uegk-10] = 333;
                        fs1[uegk+10] = 333;
                        fs1[uegk] = 333;
                    }
                    if(uegk+10 <= 99 && fs1[uegk+10] == 33 && fs1[uegk+20] == 33){
                        fs1[uegk+10] = 333;
                        fs1[uegk+20] = 333;
                        fs1[uegk] = 333;
                    }        
                }
                if(uegk-20 >= 0 && fs1[uegk-20] == 33 && fs1[uegk-10] == 33){
                    fs1[uegk-20] = 333;
                    fs1[uegk-10] = 333;
                    fs1[uegk] = 333;
                }
                if(uegk-10 >= 0 && fs1[uegk-10] == 33 && fs1[uegk+10] == 33){
                    fs1[uegk-10] = 333;
                    fs1[uegk+10] = 333;
                    fs1[uegk] = 333;
                }
                if(uegk+10 <= 99 && uegk+20 <= 99 && fs1[uegk+10] == 33 && fs1[uegk+20] == 33){
                    fs1[uegk+10] = 333;
                    fs1[uegk+20] = 333;
                    fs1[uegk] = 333;
                }
                if(zeile == 8){
                    if(uegk-20 >= 0 && fs1[uegk-20] == 33 && fs1[uegk-10] == 33){
                        fs1[uegk-20] = 333;
                        fs1[uegk-10] = 333;
                        fs1[uegk] = 333;
                    }
                    if(uegk-10 >= 0 && fs1[uegk-10] == 33 && fs1[uegk+10] == 33){
                        fs1[uegk-10] = 333;
                        fs1[uegk+10] = 333;
                        fs1[uegk] = 333;
                    }        
                }
                if(spalte > 0){
                    if(spalte == 1){
                        if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                            fs1[uegk-1] = 333;
                            fs1[uegk+1] = 333;
                            fs1[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                            fs1[uegk+1] = 333;
                            fs1[uegk+2] = 333;
                            fs1[uegk] = 333;
                        }        
                    }
                    if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                        fs1[uegk-2] = 333;
                        fs1[uegk-1] = 333;
                        fs1[uegk] = 333;
                    }
                    if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                        fs1[uegk-1] = 333;
                        fs1[uegk+1] = 333;
                        fs1[uegk] = 333;
                    }
                    if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                        fs1[uegk+1] = 333;
                        fs1[uegk+2] = 333;
                        fs1[uegk] = 333;
                    }
                    if(spalte == 8){
                        if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                            fs1[uegk-2] = 333;
                            fs1[uegk-1] = 333;
                            fs1[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                            fs1[uegk-1] = 333;
                            fs1[uegk+1] = 333;
                            fs1[uegk] = 333;
                        }            
                    }
                }else{
                    if(spalte == 0){
                        if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                            fs1[uegk+1] = 333; 
                            fs1[uegk+2] = 333; 
                            fs1[uegk] = 333;
                        }          
                    }
                }
                if (spalte < 9){
                    if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                        fs1[uegk-2] = 333; 
                        fs1[uegk-1] = 333; 
                        fs1[uegk] = 333;
                    }
                    if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                        fs1[uegk-1] = 333; 
                        fs1[uegk+1] = 333; 
                        fs1[uegk] = 333;
                    }
                    if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                        fs1[uegk+1] = 333; 
                        fs1[uegk+2] = 333; 
                        fs1[uegk] = 333;
                    }
                }else{
                    if(spalte == 9){
                        if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1]== 33){
                            fs1[uegk-2] = 333;
                            fs1[uegk-1] = 333; 
                            fs1[uegk] = 333;
                        }            
                    }
                }
            }else{
                if(zeile == 0){
                    if(uegk+10 <= 99 && fs1[uegk+10] == 33 && fs1[uegk+20] == 33){
                        fs1[uegk+10] = 333;
                        fs1[uegk+20] = 333; 
                        fs1[uegk] = 333;
                    }
                    if(spalte > 0){
                        if(spalte == 1){
                            if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                                fs1[uegk-1] = 333;
                                fs1[uegk+1] = 333;
                                fs1[uegk] = 333;
                            }
                            if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                                fs1[uegk+1] = 333;
                                fs1[uegk+2] = 333;
                                fs1[uegk] = 333;
                            }        
                        }
                        if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                            fs1[uegk-2] = 333;
                            fs1[uegk-1] = 333;
                            fs1[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                            fs1[uegk-1] = 333;
                            fs1[uegk+1] = 333;
                            fs1[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                            fs1[uegk+1] = 333;
                            fs1[uegk+2] = 333;
                            fs1[uegk] = 333;
                        }
                        if(spalte == 8){
                            if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                                fs1[uegk-2] = 333;
                                fs1[uegk-1] = 333;
                                fs1[uegk] = 333;
                            }
                            if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                                fs1[uegk-1] = 333;
                                fs1[uegk+1] = 333;
                                fs1[uegk] = 333;
                            }            
                        }
                    }else{
                        if(spalte == 0){
                            if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2]== 33){
                                fs1[uegk+1] = 333;
                                fs1[uegk+2] = 333;
                                fs1[uegk] = 333;
                            }         
                        }
                    }
                    if(spalte < 9){
                        if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                            fs1[uegk-2] = 333;
                            fs1[uegk-1] = 333; 
                            fs1[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                            fs1[uegk-1] = 333; 
                            fs1[uegk+1] = 333; 
                            fs1[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                            fs1[uegk+1] = 333; 
                            fs1[uegk+2] = 333; 
                            fs1[uegk] = 333;
                        }
                    }else{
                        if(spalte == 9){
                            if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1]== 33){
                                fs1[uegk-2] = 333;
                                fs1[uegk-1] = 333; 
                                fs1[uegk] = 333;
                            }        
                        }
                    }
                }
            }
            if(zeile < 9){
                if(zeile == 1){
                    if(uegk-10 >= 0 && fs1[uegk-10] == 33 && fs1[uegk+10] == 33){
                        fs1[uegk-10] = 333;
                        fs1[uegk+10] = 333;
                        fs1[uegk] = 333;
                    }
                    if(uegk+10 <= 99 && fs1[uegk+10] == 33 && fs1[uegk+20] == 33){
                        fs1[uegk+10] = 333;
                        fs1[uegk+20] = 333;
                        fs1[uegk] = 333;
                    }        
                }
                if(uegk-20 >= 0 && fs1[uegk-20] == 33 && fs1[uegk-10] == 33){
                    fs1[uegk-20] = 333;
                    fs1[uegk-10] = 333;
                    fs1[uegk] = 333;
                }
                if(uegk-10 >= 0 && fs1[uegk-10] == 33 && fs1[uegk+10] == 33){
                    fs1[uegk-10] = 333;
                    fs1[uegk+10] = 333;
                    fs1[uegk] = 333;
                }
                if(uegk+10 <= 99 && uegk + 20 <= 99 && fs1[uegk+10] == 33 && fs1[uegk+20] == 33){
                    fs1[uegk+10] = 333;
                    fs1[uegk+20] = 333;
                    fs1[uegk] = 333;
                }
                if(zeile == 8){
                    if(uegk-20 >= 0 && fs1[uegk-20] == 33 && fs1[uegk-10] == 33){
                        fs1[uegk-20] = 333;
                        fs1[uegk-10] = 333;
                        fs1[uegk] = 333;
                    }
                    if(uegk-10 >= 0 && fs1[uegk-10] == 33 && fs1[uegk+10] == 33){
                        fs1[uegk-10] = 333;
                        fs1[uegk+10] = 333;
                        fs1[uegk] = 333;
                    }        
                }
                if(spalte > 0){
                    if(spalte == 1){
                        if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                            fs1[uegk-1] = 333;
                            fs1[uegk+1] = 333;
                            fs1[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                            fs1[uegk+1] = 333;
                            fs1[uegk+2] = 333;
                            fs1[uegk] = 333;
                        }        
                    }
                    if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                        fs1[uegk-2] = 333;
                        fs1[uegk-1] = 333;
                        fs1[uegk] = 333;
                    }
                    if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                        fs1[uegk-1] = 333;
                        fs1[uegk+1] = 333;
                        fs1[uegk] = 333;
                    }
                    if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                        fs1[uegk+1] = 333;
                        fs1[uegk+2] = 333;
                        fs1[uegk] = 333;
                    }
                    if(spalte == 8){
                        if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                            fs1[uegk-2] = 333;
                            fs1[uegk-1] = 333;
                            fs1[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                            fs1[uegk-1] = 333;
                            fs1[uegk+1] = 333;
                            fs1[uegk] = 333;
                        }            
                    }
                }else{
                    if(spalte == 0){
                        if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                            fs1[uegk+1] = 333; 
                            fs1[uegk+2] = 333; 
                            fs1[uegk] = 333;
                        }             
                    }
                }
                if (spalte < 9){
                    if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                        fs1[uegk-2] = 333; 
                        fs1[uegk-1] = 333; 
                        fs1[uegk] = 333;
                    }
                    if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                        fs1[uegk-1] = 333; 
                        fs1[uegk+1] = 333; 
                        fs1[uegk] = 333;
                    }
                    if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                        fs1[uegk+1] = 333; 
                        fs1[uegk+2] = 333; 
                        fs1[uegk] = 333;
                    }
                }else{
                    if(spalte == 9){
                        if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1]== 33){
                            fs1[uegk-2] = 333;
                            fs1[uegk-1] = 333; 
                            fs1[uegk] = 333;
                        }            
                    }
                }
            }else{
                if(zeile == 9){
                    if(uegk-10 >= 0 && fs1[uegk-10] == 33 && fs1[uegk-20] == 33){
                        fs1[uegk-10] = 333;
                        fs1[uegk-20] = 333; 
                        fs1[uegk] = 333;
                    }
                    if(spalte > 0){
                        if(spalte == 1){
                            if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                                fs1[uegk-1] = 333;
                                fs1[uegk+1] = 333;
                                fs1[uegk] = 333;
                            }
                            if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                                fs1[uegk+1] = 333;
                                fs1[uegk+2] = 333;
                                fs1[uegk] = 333;
                            }        
                        }
                        if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                            fs1[uegk-2] = 333;
                            fs1[uegk-1] = 333;
                            fs1[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && uegk+1 <= 99 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                            fs1[uegk-1] = 333;
                            fs1[uegk+1] = 333;
                            fs1[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && uegk+2 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                            fs1[uegk+1] = 333;
                            fs1[uegk+2] = 333;
                            fs1[uegk] = 333;
                        }
                        if(spalte == 8){
                            if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                                fs1[uegk-2] = 333;
                                fs1[uegk-1] = 333;
                                fs1[uegk] = 333;
                            }
                            if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                                fs1[uegk-1] = 333;
                                fs1[uegk+1] = 333;
                                fs1[uegk] = 333;
                            }            
                        }
                    }else{
                        if(spalte == 0){
                            if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2]== 33){
                                fs1[uegk+1] = 333;
                                fs1[uegk+2] = 333; 
                                fs1[uegk] = 333;
                            }       
                        }
                    }
                    if(spalte < 9){
                        if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1] == 33){
                            fs1[uegk-2] = 333; 
                            fs1[uegk-1] = 333; 
                            fs1[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && fs1[uegk-1] == 33 && fs1[uegk+1] == 33){
                            fs1[uegk-1] = 333; 
                            fs1[uegk+1] = 333; 
                            fs1[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && fs1[uegk+1] == 33 && fs1[uegk+2] == 33){
                            fs1[uegk+1] = 333; 
                            fs1[uegk+2] = 333; 
                            fs1[uegk] = 333;
                        }
                    }else{
                        if(spalte == 9){
                            if(uegk-2 >= 0 && fs1[uegk-2] == 33 && fs1[uegk-1]== 33){
                                fs1[uegk-2] = 333;
                                fs1[uegk-1] = 333; 
                                fs1[uegk] = 333;
                            }       
                        }
                    }
                }
            }        
        }
        return;
    }

    public void umkreisPruefenKreuzer2(int uegk){
        int zeile = uegk/10;
        int spalte = uegk%10;

        if(fs2[uegk] == 33){
            if(zeile > 0 && zeile < 9){
                if(zeile == 1){
                    if(uegk-10 >= 0 && fs2[uegk-10] == 33 && fs2[uegk+10] == 33){
                        fs2[uegk-10] = 333;
                        fs2[uegk+10] = 333;
                        fs2[uegk] = 333;
                    }
                    if(uegk+10 <= 99 && fs2[uegk+10] == 33 && fs2[uegk+20] == 33){
                        fs2[uegk+10] = 333;
                        fs2[uegk+20] = 333;
                        fs2[uegk] = 333;
                    }        
                }
                if(uegk-20 >= 0 && fs2[uegk-20] == 33 && fs2[uegk-10] == 33){
                    fs2[uegk-20] = 333;
                    fs2[uegk-10] = 333;
                    fs2[uegk] = 333;
                }
                if(uegk-10 >= 0 && fs2[uegk-10] == 33 && fs2[uegk+10] == 33){
                    fs2[uegk-10] = 333;
                    fs2[uegk+10] = 333;
                    fs2[uegk] = 333;
                }
                if(uegk+10 <= 99 && uegk+20 <= 99 && fs2[uegk+10] == 33 && fs2[uegk+20] == 33){
                    fs2[uegk+10] = 333;
                    fs2[uegk+20] = 333;
                    fs2[uegk] = 333;
                }
                if(zeile == 8){
                    if(uegk-20 >= 0 && fs2[uegk-20] == 33 && fs2[uegk-10] == 33){
                        fs2[uegk-20] = 333;
                        fs2[uegk-10] = 333;
                        fs2[uegk] = 333;
                    }
                    if(uegk-10 >= 0 && fs2[uegk-10] == 33 && fs2[uegk+10] == 33){
                        fs2[uegk-10] = 333;
                        fs2[uegk+10] = 333;
                        fs2[uegk] = 333;
                    }        
                }
                if(spalte > 0){
                    if(spalte == 1){
                        if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                            fs2[uegk-1] = 333;
                            fs2[uegk+1] = 333;
                            fs2[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                            fs2[uegk+1] = 333;
                            fs2[uegk+2] = 333;
                            fs2[uegk] = 333;
                        }        
                    }
                    if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                        fs2[uegk-2] = 333;
                        fs2[uegk-1] = 333;
                        fs2[uegk] = 333;
                    }
                    if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                        fs2[uegk-1] = 333;
                        fs2[uegk+1] = 333;
                        fs2[uegk] = 333;
                    }
                    if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                        fs2[uegk+1] = 333;
                        fs2[uegk+2] = 333;
                        fs2[uegk] = 333;
                    }
                    if(spalte == 8){
                        if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                            fs2[uegk-2] = 333;
                            fs2[uegk-1] = 333;
                            fs2[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                            fs2[uegk-1] = 333;
                            fs2[uegk+1] = 333;
                            fs2[uegk] = 333;
                        }            
                    }
                }else{
                    if(spalte == 0){
                        if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                            fs2[uegk+1] = 333; 
                            fs2[uegk+2] = 333; 
                            fs2[uegk] = 333;
                        }          
                    }
                }
                if (spalte < 9){
                    if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                        fs2[uegk-2] = 333; 
                        fs2[uegk-1] = 333; 
                        fs2[uegk] = 333;
                    }
                    if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                        fs2[uegk-1] = 333; 
                        fs2[uegk+1] = 333; 
                        fs2[uegk] = 333;
                    }
                    if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                        fs2[uegk+1] = 333; 
                        fs2[uegk+2] = 333; 
                        fs2[uegk] = 333;
                    }
                }else{
                    if(spalte == 9){
                        if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1]== 33){
                            fs2[uegk-2] = 333;
                            fs2[uegk-1] = 333; 
                            fs2[uegk] = 333;
                        }            
                    }
                }
            }else{
                if(zeile == 0){
                    if(uegk+10 <= 99 && fs2[uegk+10] == 33 && fs2[uegk+20] == 33){
                        fs2[uegk+10] = 333;
                        fs2[uegk+20] = 333; 
                        fs2[uegk] = 333;
                    }
                    if(spalte > 0){
                        if(spalte == 1){
                            if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                                fs2[uegk-1] = 333;
                                fs2[uegk+1] = 333;
                                fs2[uegk] = 333;
                            }
                            if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                                fs2[uegk+1] = 333;
                                fs2[uegk+2] = 333;
                                fs2[uegk] = 333;
                            }        
                        }
                        if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                            fs2[uegk-2] = 333;
                            fs2[uegk-1] = 333;
                            fs2[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                            fs2[uegk-1] = 333;
                            fs2[uegk+1] = 333;
                            fs2[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                            fs2[uegk+1] = 333;
                            fs2[uegk+2] = 333;
                            fs2[uegk] = 333;
                        }
                        if(spalte == 8){
                            if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                                fs2[uegk-2] = 333;
                                fs2[uegk-1] = 333;
                                fs2[uegk] = 333;
                            }
                            if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                                fs2[uegk-1] = 333;
                                fs2[uegk+1] = 333;
                                fs2[uegk] = 333;
                            }            
                        }
                    }else{
                        if(spalte == 0){
                            if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2]== 33){
                                fs2[uegk+1] = 333;
                                fs2[uegk+2] = 333;
                                fs2[uegk] = 333;
                            }         
                        }
                    }
                    if(spalte < 9){
                        if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                            fs2[uegk-2] = 333;
                            fs2[uegk-1] = 333; 
                            fs2[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                            fs2[uegk-1] = 333; 
                            fs2[uegk+1] = 333; 
                            fs2[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                            fs2[uegk+1] = 333; 
                            fs2[uegk+2] = 333; 
                            fs2[uegk] = 333;
                        }
                    }else{
                        if(spalte == 9){
                            if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1]== 33){
                                fs2[uegk-2] = 333;
                                fs2[uegk-1] = 333; 
                                fs2[uegk] = 333;
                            }        
                        }
                    }
                }
            }
            if(zeile < 9){
                if(zeile == 1){
                    if(uegk-10 >= 0 && fs2[uegk-10] == 33 && fs2[uegk+10] == 33){
                        fs2[uegk-10] = 333;
                        fs2[uegk+10] = 333;
                        fs2[uegk] = 333;
                    }
                    if(uegk+10 <= 99 && fs2[uegk+10] == 33 && fs2[uegk+20] == 33){
                        fs2[uegk+10] = 333;
                        fs2[uegk+20] = 333;
                        fs2[uegk] = 333;
                    }        
                }
                if(uegk-20 >= 0 && fs2[uegk-20] == 33 && fs2[uegk-10] == 33){
                    fs2[uegk-20] = 333;
                    fs2[uegk-10] = 333;
                    fs2[uegk] = 333;
                }
                if(uegk-10 >= 0 && fs2[uegk-10] == 33 && fs2[uegk+10] == 33){
                    fs2[uegk-10] = 333;
                    fs2[uegk+10] = 333;
                    fs2[uegk] = 333;
                }
                if(uegk+10 <= 99 && uegk + 20 <= 99 && fs2[uegk+10] == 33 && fs2[uegk+20] == 33){
                    fs2[uegk+10] = 333;
                    fs2[uegk+20] = 333;
                    fs2[uegk] = 333;
                }
                if(zeile == 8){
                    if(uegk-20 >= 0 && fs2[uegk-20] == 33 && fs2[uegk-10] == 33){
                        fs2[uegk-20] = 333;
                        fs2[uegk-10] = 333;
                        fs2[uegk] = 333;
                    }
                    if(uegk-10 >= 0 && fs2[uegk-10] == 33 && fs2[uegk+10] == 33){
                        fs2[uegk-10] = 333;
                        fs2[uegk+10] = 333;
                        fs2[uegk] = 333;
                    }        
                }
                if(spalte > 0){
                    if(spalte == 1){
                        if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                            fs2[uegk-1] = 333;
                            fs2[uegk+1] = 333;
                            fs2[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                            fs2[uegk+1] = 333;
                            fs2[uegk+2] = 333;
                            fs2[uegk] = 333;
                        }        
                    }
                    if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                        fs2[uegk-2] = 333;
                        fs2[uegk-1] = 333;
                        fs2[uegk] = 333;
                    }
                    if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                        fs2[uegk-1] = 333;
                        fs2[uegk+1] = 333;
                        fs2[uegk] = 333;
                    }
                    if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                        fs2[uegk+1] = 333;
                        fs2[uegk+2] = 333;
                        fs2[uegk] = 333;
                    }
                    if(spalte == 8){
                        if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                            fs2[uegk-2] = 333;
                            fs2[uegk-1] = 333;
                            fs2[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                            fs2[uegk-1] = 333;
                            fs2[uegk+1] = 333;
                            fs2[uegk] = 333;
                        }            
                    }
                }else{
                    if(spalte == 0){
                        if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                            fs2[uegk+1] = 333; 
                            fs2[uegk+2] = 333; 
                            fs2[uegk] = 333;
                        }             
                    }
                }
                if (spalte < 9){
                    if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                        fs2[uegk-2] = 333; 
                        fs2[uegk-1] = 333; 
                        fs2[uegk] = 333;
                    }
                    if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                        fs2[uegk-1] = 333; 
                        fs2[uegk+1] = 333; 
                        fs2[uegk] = 333;
                    }
                    if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                        fs2[uegk+1] = 333; 
                        fs2[uegk+2] = 333; 
                        fs2[uegk] = 333;
                    }
                }else{
                    if(spalte == 9){
                        if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1]== 33){
                            fs2[uegk-2] = 333;
                            fs2[uegk-1] = 333; 
                            fs2[uegk] = 333;
                        }            
                    }
                }
            }else{
                if(zeile == 9){
                    if(uegk-10 >= 0 && fs2[uegk-10] == 33 && fs2[uegk-20] == 33){
                        fs2[uegk-10] = 333;
                        fs2[uegk-20] = 333; 
                        fs2[uegk] = 333;
                    }
                    if(spalte > 0){
                        if(spalte == 1){
                            if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                                fs2[uegk-1] = 333;
                                fs2[uegk+1] = 333;
                                fs2[uegk] = 333;
                            }
                            if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                                fs2[uegk+1] = 333;
                                fs2[uegk+2] = 333;
                                fs2[uegk] = 333;
                            }        
                        }
                        if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                            fs2[uegk-2] = 333;
                            fs2[uegk-1] = 333;
                            fs2[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && uegk+1 <= 99 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                            fs2[uegk-1] = 333;
                            fs2[uegk+1] = 333;
                            fs2[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && uegk+2 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                            fs2[uegk+1] = 333;
                            fs2[uegk+2] = 333;
                            fs2[uegk] = 333;
                        }
                        if(spalte == 8){
                            if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                                fs2[uegk-2] = 333;
                                fs2[uegk-1] = 333;
                                fs2[uegk] = 333;
                            }
                            if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                                fs2[uegk-1] = 333;
                                fs2[uegk+1] = 333;
                                fs2[uegk] = 333;
                            }            
                        }
                    }else{
                        if(spalte == 0){
                            if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2]== 33){
                                fs2[uegk+1] = 333;
                                fs2[uegk+2] = 333; 
                                fs2[uegk] = 333;
                            }       
                        }
                    }
                    if(spalte < 9){
                        if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1] == 33){
                            fs2[uegk-2] = 333; 
                            fs2[uegk-1] = 333; 
                            fs2[uegk] = 333;
                        }
                        if(uegk-1 >= 0 && fs2[uegk-1] == 33 && fs2[uegk+1] == 33){
                            fs2[uegk-1] = 333; 
                            fs2[uegk+1] = 333; 
                            fs2[uegk] = 333;
                        }
                        if(uegk+1 <= 99 && fs2[uegk+1] == 33 && fs2[uegk+2] == 33){
                            fs2[uegk+1] = 333; 
                            fs2[uegk+2] = 333; 
                            fs2[uegk] = 333;
                        }
                    }else{
                        if(spalte == 9){
                            if(uegk-2 >= 0 && fs2[uegk-2] == 33 && fs2[uegk-1]== 33){
                                fs2[uegk-2] = 333;
                                fs2[uegk-1] = 333; 
                                fs2[uegk] = 333;
                            }       
                        }
                    }
                }
            }        
        }
        return;
    }
}
