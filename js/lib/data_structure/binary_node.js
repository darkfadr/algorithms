var BinaryNode = function(data, left, right){
	this._data 	= data; 
	this._left 	= left;
	this._right = right;
}	

BinaryNode.prototype = {
	constructor: BinaryNode.apply(this, [data, null, null]),
	size: function(node){
		if(node == null){
			return 0;
		} else {
			return 1 + size(node._left) + size(node._right); 
		}
	}
}

module.exports = BinaryNode;
