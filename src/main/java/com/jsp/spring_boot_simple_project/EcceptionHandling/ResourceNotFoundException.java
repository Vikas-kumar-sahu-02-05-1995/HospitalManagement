package com.jsp.spring_boot_simple_project.EcceptionHandling;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	long fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message, Throwable cause  ) {
		super(message, cause);
	}

}




//public void swapDesktopBetweenPersons(Long Id1, Long Id2) {
//    
//    Desktop desktop = desktopRepository.findById(Id1)
//        .orElseThrow(() -> new ResourceNotFoundException("Laptop 1 not found"));
//    Person person1 =desktop.getPerson();
//
//    Desktop desktop1 = desktopRepository.findById(Id2)
//        .orElseThrow(() -> new ResourceNotFoundException("Laptop 2 not found"));
//    Person person2 = desktop1.getPerson();
//
//    desktop.setPerson(person2);
//    desktop1.setPerson(person1);
//
//    desktopRepository.save(desktop);
//    desktopRepository.save(desktop1);
//}



//@PutMapping("/swap")
//public ResponseEntity<String> swapDesktops(@RequestParam Long Id1, @RequestParam Long Id2) {
//    desktopService.swapDesktopBetweenPersons(Id1, Id1);
//    return ResponseEntity.ok("Laptops swapped successfully");
//}