package ordenacao;

import java.util.Random;

public class otimi{


	//___Selection Sort___
	static void selection(int[] arr){
		int left = 0,
			right = arr.length;

		while(left < right){
			int lowest = left,
				max = left;
		
			for(int i=left; i<right; i++){
				if(arr[i] < arr[lowest])
					lowest = i;
				if(arr[i] > arr[max])
					max = i;
			}

			int tmp = arr[lowest];
			arr[lowest] = arr[left];
			arr[left] = tmp;

			if(max==left)
				max = lowest;

			tmp = arr[max];
			arr[max] = arr[right-1];
			arr[right-1] = tmp;

			left++;
			right--;
		}
	}


	//___Insertion Sort___
	static void insertion(int[] arr){
		int n = arr.length;
		for(int i=1; i<n; i++){
			int tmp = arr[i];
			int j = i-1;
			int newPos = insertionBinarySearch(arr, 0, i, arr[i]); // Otimizado pois utiliza a pesquisa binaria para encontrar a posicao da substituicao
			while(j >= newPos){ // Isso reduz a quantidade de comparacoes de \Theta(n^2) para \Theta(n.lg(n))
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = tmp;
		}
	}

	static int insertionBinarySearch(int[] arr, int L, int R, int target){
		if(L > R) return L;
		else{
			int mid = (L+R)/2;
			if(arr[mid] == target)
				return mid + 1;
			else if(arr[mid] < target)
				return insertionBinarySearch(arr, mid+1, R, target);
			else
				return insertionBinarySearch(arr, L, mid-1, target);
		}
	}


	//___Bubble Sort___
	static void bubble(int[] arr){
		bubble(arr, arr.length-1);
	}

	static void bubble(int[] arr, int last){
		int ult = 0; // Algoritmo otimizado pois guarda a posicao da ultima troca, e so repete ate ela.
		for(int i=0; i<last; i++){
			if(arr[i]>arr[i+1]){
				int tmp = arr[i];
				arr[i] = arr[i+1];
				arr[i+1] = tmp;
				ult = i;
			}
		}
		if(ult>0) bubble(arr, ult); // Isso funciona pois no bubble, o final do array representa a parte ja ordenada.
	}


	//_________________________________________________________
	public static void printArr(int[] arr){
		int reps = arr.length - 1;
		for(int i=0; i<reps; i++){
			System.out.print(arr[i] + " - ");
		}
		System.out.println(arr[reps]);
	}

	public static void main(String[] args){
		Random rnd = new Random();
		rnd.setSeed(69420);
		int[] arr = new int[10];
		for(int i=0; i<arr.length; i++){
			arr[i] = (int) Math.abs(rnd.nextInt()) % 100;
		}
		System.out.println("original:");
		printArr(arr);

		System.out.println("\nordenado:");
		selection(arr);
		printArr(arr);
	}
}
