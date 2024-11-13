void prucurarArvore (Lista* lista, No* i, int num) {
    if(i!=NULL) {
        if(i->numero == num) {
            inserirLista(lista, num);
            break;
        } else if (num <i -> numero) {
            procurarArvore(lista, i -> esq, num);
        } else if (num > i -> numero) {
            procurarArvore(lista, i->dir, num);
        }
    }
}