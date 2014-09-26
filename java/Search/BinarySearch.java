

public class BinarySearch{



	public static boolean bsearch(int x, int[] arr, int low, int high){

		if(high < low)
			return false;
		int mid =  (high + low) / 2;
		if(arr[mid] == x)
			return true;		
		if(arr[mid] > x)
			return bsearch(x, arr, low, mid -1 );
		else //arr[mid ] < xs
			return bsearch(x, arr, mid + 1, high );

	}

	public static boolean bsearch(int x, int [] arr){
		return bsearch(x, arr, 0, arr.length-1);
	}

	public static void main(String[] args){
		int[] arr = {1,2,3,5,6,8,9,10};

		System.out.println(bsearch(3,arr));
		System.out.println(bsearch(11,arr));

	}

}