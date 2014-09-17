

public final class Sort{

	public static void quicksort( int [] a ){
		quicksort( a, 0, a.length - 1);
	}
	
	public static void swap(int [] a, int index1, int index2){
		int temp = a[ index1 ];
		a[ index1 ] = a[ index2 ];
		a[ index2 ] = temp;
	}
	
	//easy to remember 
	private static void quicksort(int [] a, int left, int right){
		
		if(left < right) // else => n == 1 which is the base case
		{
			//choose pivot from middle and swap it with the first index
			int middle = ( left + right ) / 2;
			int pivot = a[ middle ];
			swap( a, left, middle );
		
			
			//partition around pivot
			int i = left + 1;
			for( int j = left + 1; j <= right; j++ ){
				if( a[j] < pivot ){
					swap( a, i, j );
					i++;
				}
			}
			swap( a, left, i - 1 ); //swap pivot in between the 2 partitions
			
			
			//recursively sort the two partitions
			quicksort( a, left, i - 1 );
			quicksort( a, i, right );
		}
	}
	
	public static void main(String []  args){
	
		int []  arr = {5,9, 8 ,5 ,3, 1, 2, 4};
		quicksort(arr);
		for(int x : arr)
			System.out.print(x + ", ");
		
	}

}
	

