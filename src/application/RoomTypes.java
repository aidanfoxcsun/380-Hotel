package application;

// Enum of possible Room Types. 
// Created to prevent hard to track errors that could arise from using strings and possibly having typos the compiler can't catch.
/**
 * 
 * @author Aidan Fox
 * Date: 4/15/24
 * Description: Simple enum to store the 4 different Room types available for a HotelRoom object. Created in order to avoid any possible errors that could arise from storing the types as Strings.
 *
 */
public enum RoomTypes {
	SINGLE,
	DOUBLE,
	KING,
	SUITE,
	__PLACEHOLDER__ // only used for default constructor. Should NEVER be used as an actual type.
}
