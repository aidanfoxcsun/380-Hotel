package application;

// Enum of possible Room Types. 
// Created to prevent hard to track errors that could arise from using strings and possibly having typos the compiler can't catch.
public enum RoomTypes {
	SINGLE,
	DOUBLE,
	KING,
	SUITE,
	__PLACEHOLDER__ // only used for default constructor. Should NEVER be used as an actual type.
}
