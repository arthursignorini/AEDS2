class no {
    public int elemento;
    public int esq, dir;
    public int nivel;

    public no(int elemento) {
        this(elemento,null,null,1);
    }

    public no(int elemento, No esq, No dir, int nivel) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
		this.nivel = nivel;
	}

    public void setNivel() {
		this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
	}

    public static int getNivel(No no) {
		return (no == null) ? 0 : no.nivel;
	}
}

public class avl {
    private no raiz;

    public avl() {
        raiz = null;
    }

    public boolean pesquisar(int x) {
        return pesquisar(raiz, x);
    }

    public boolean pesquisar(no i, int x) {
        boolean resp;
        if(i == null) {
            resp = false;
        } else if(i.elemento == x) {
            resp = true;
        } else if(x < i.elemento) {
            resp = pesquisar(i.esq, x);
        } else if(x > i.elemento) {
            resp = pesquisar(i.dir, x);
        }
        return resp;
    }

    public void inserir(int x) {
        raiz = inserir(raiz, x);
    }

    public no inserir(no i, int x) {
        if(i == null) {
            i = new no(x);
        } else if(x < i.elemento) {
            i.esq = inserir(i.esq, x);
        } else if(x > i.elemento) {
            i.dir = inserir(i.dir, x);
        } else {
            System.out.println("Elemento já está na lista");
        }
        return balancear(i);
    }

    public no balancear(no no) {
        if(no!=null) {
            int fatorBalancemento = no.getNivel(no.dir) - no.getNivel(no.esq);
            if(fatorBalancemento >= -1 && fatorBalancemento <= 1) {
                no.setNivel();
            } else if (fatorBalancemento == 2) {
                int fatorFilho = no.getNivel(no.dir.dir) - no.getNivel(no.dir.esq);
                if(fatorFilho == -1) {
                    no.dir = rotDir(no.dir);
                }
                no = rotEsq(no);
            } else if(fatorBalancemento == -2) {
                int fatorFilho = no.getNivel(no.esq.dir) - no.getNivel(no.esq.esq);
                if(fatorFilho == 1) {
                    no.esq = rotEsq(no.esq);
                }
                no = rotDir(no);
            } else {
                System.out.println("Erro ao balancear no com fator de balanceamento invalido: " + fatorBalancemento);       
            }
        }
        return no;
    }

    private no rotDir(no no) {
        no noEsq = no.esq;
        no noEsqDir = noEsq.dir;
    
        noEsq.dir = no;
        no.esq = noEsqDir;
    
        no.setNivel(); 
        noEsq.setNivel(); 
    
        return noEsq;
    }
    
    private no rotEsq(no no) {
        no noDir = no.dir;
        no noDirEsq = noDir.esq;
    
        noDir.esq = no;
        no.dir = noDirEsq;
    
        no.setNivel(); 
        noDir.setNivel(); 
    
        return noDir;
    }
    
}
