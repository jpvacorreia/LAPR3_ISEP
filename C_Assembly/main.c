#include <stdio.h>
#include <stdlib.h>
#include "asm.h"
#include <glob.h>
#include <string.h>
#include <errno.h>
#include <sys/types.h>
#include <linux/inotify.h>

#define EVENT_SIZE ( sizeof (struct inotify_event) )
#define EVENT_BUF_LEN (1024 * (EVENT_SIZE + 16))

int main(){
	int j=0;
	
	while (j<10){
	int length, i;
	int fd;
	int wd;
	char buffer[EVENT_BUF_LEN];
	
	fd = inotify_init();
	
	if (fd < 0){
		perror("inotify_init");
	}
	
	wd = inotify_add_watch(fd,"/media/partilha/lapr3", IN_CREATE);
	
	length = read (fd, buffer, EVENT_BUF_LEN);
	
	if (length <0) { perror("read") ; }
	i=0;
	while (i < length){
		struct inotify_event *event = ( struct inotify_event * ) &buffer[i];
		if (event->len){
			if (event->mask & IN_MOVED_TO || event->mask & IN_CREATE) {
				
					glob_t paths;
					int retval;
    
					paths.gl_pathc = 0;
					paths.gl_pathv = NULL;
					paths.gl_offs = 0;

					retval = glob( "lock_*.data", GLOB_NOCHECK | GLOB_NOSORT,
							NULL, &paths );
					if( retval == 0 ) {
						int idx;
						char * filename;
        
						for( idx = 0; idx < paths.gl_pathc; idx++ ) {
							printf( "%s\n", 
							paths.gl_pathv[idx] );
                                                           
							FILE *fpointer;
							fpointer = fopen(paths.gl_pathv[idx],"r");
							char line1[20];
							int values[3];
							int k = 0;
							unsigned int *vec1 = (unsigned int*) malloc (3* sizeof(int));
							
							while(!feof(fpointer)){
								
								fgets(line1,20,fpointer);
								*(vec1 + k) = atof(line1);
								k++;
							}
							
							fclose(fpointer);
							
							int p = *(vec1) * 100;
							int p2 = *(vec1+1);
							int p3 = *(vec1+2) * 100;
							
							
							double value = (double) estimate(p2,p,p3)/100;
        
							filename = paths.gl_pathv[idx];
							
        
							char *splitter = strtok(filename,"_");
							splitter = strtok(NULL,"_");
        
							char result[100] = {0};
							snprintf(result, sizeof(result), "estimate_%s", splitter);
		
							char value_char[50];
							sprintf(value_char,"%f",value);
							FILE *fpointer2;
							fpointer2 = fopen(result,"w");
							fprintf(fpointer2, value_char);
							fclose(fpointer2);
							
							free(vec1);
							}
        
						globfree(&paths);  
							
					} else {
						puts( "No file found." );
					
				}
			}
		}
		i += EVENT_SIZE + event->len;
	}
	
	inotify_rm_watch(fd,wd);
	
	close(fd);
	j++;
}
	
	return 0;
}
