# ABS Items;
  These are different than standard items, and they are expanded functionality-wise from RedItems.
  
## Key Differences;

  ### NBT and JSON access Key Chains
  
	Recursive access to JSON and NBT data using a list of strings.
	
  ### Streamlined simple access to all three types of persistent data.
  
	Can define a type of data retrieved from a small list of options. (number, bool, string, (json/nbt))
    
  ### ABSItem
  
    Contains multiple additional default data types for levelup functionality.
    Contains multiple specific convenience changes from the initial RedItems implemented effects.
    
  ### ABSEffect
  
    Contains multiple methods designed to ease the pain of effect construction.
    
    
## Utils

  ### AbsItemManager
  
    Includes multiple simple access methods for creating and leveling ABSItems and standard RedItems.
    
  ### AbsLevelUtil
  
    Includes levelup checks, adding/removing/leveling effects in SimpleContainer, or JSON keychains, or NBT keychains.
    Main functional methods will include both JSON and NBT access.
    
  ### AbsItemUtil
  
    Includes multiple generic functions for quickly creating RedItems with multiple effects.
    
  ### AbsJsonUtil, AbsNBTUtil
  
    Includes multiple options for adding, removing, and replacing persistent data using key chains.
    
  ### LocUtil
  
	Included from former projects for utility purposes.
