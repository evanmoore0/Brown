from functools import reduce

class InvalidInputException(Exception):
	def __str__(self):
		return "Invalid Input Given."

def apply_all(f_list, n):
	"""apply_all: [function], number -> [number]
	Purpose: applies each function in a list to a single number
	Consumes: a list of functions and a number
	Produces: a list of numbers where each number is the result of a function application
	Exceptions: InvalidInputException if any of the inputs are None
	Example: function_apply([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4) --> [5,6,7]
	"""
	if f_list == None or n == None:
    	 raise InvalidInputException("Please input valid functions and a valid number")


	return list(map(lambda x: x(n), f_list))

def compose(f_list, n):
	"""compose: [function], number -> number
	Purpose: composes all of the functions in a list and applies this to a number
	Consumes: a list of functions and a number
	Produces: a number that is the result of composing all of the functions in the list
			and applying this to the number argument
	Exceptions: InvalidInputExceiption if any of the inputs are None
	Example: compose([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4) --> 10
	"""

	if f_list == None or n == None:
    	 raise InvalidInputException("Please input valid functions and a valid number")

	
	return reduce(lambda x, y: y(x), f_list, n)

def list_compose_steps(f_list, n):
	"""list_compose_steps: [function], number -> [number]
	Purpose: shows all intermediate steps of compose starting with the input number n
	Consumes: a list of functions and a number
	Produces: a list of numbers that represent the intermediate steps of compose
	Exceptions: InvalidInputExceiption if any of the inputs are None
	Example: list_compose_steps([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4)
			--> [4, 5, 7, 10]
	"""
	if f_list == None or n == None:
    	 raise InvalidInputException("Please input valid functions and a valid number")

	return reduce(lambda x, y: x + list(map(y, [x[len(x)-1]])), f_list, [n])