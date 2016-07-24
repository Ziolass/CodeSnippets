setwd("C:/kodySAS")

rm(list=ls())

# # # DATA ANALYSIS # # # # # # # # # # # # # # # # # # # # #


library(rvest)        # For webscraping
library(stringr)      # For string manipulation
library(ggmap)        # For geolocalization services
library(leaflet)      # For maps
library(dplyr)
library(sqldf)
library(sp)
library(data.table)



sellers.names <- c("Name1",
					"Name2")

sellers <- c("Address1",
               "Address2",)

# Get the geocodes for the addresses from google

locations.sellers <- data.frame(Address = sellers,  # The address of the store
                        geocode(sellers),   # Lon and lat of the address
                        Seller = sellers.names,    # Name of the store
                        stringsAsFactors = F)     # Keep character format
#locations.sellers

#rm(list=ls())


write.table(locations.sellers[,2:4], file = "Path/file.txt", append = TRUE, quote = FALSE, sep = ";",
            eol = "\n", na = "NA", dec = ".", row.names = FALSE,
            col.names = TRUE, qmethod = c("escape", "double"),
            fileEncoding = "")

