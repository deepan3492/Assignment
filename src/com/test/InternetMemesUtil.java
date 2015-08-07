package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class InternetMemesUtil {
	
	public void readJsonFile() {
		
		 try
	        {
			 InputStream inputStream = 
					 InternetMemesUtil.class.getClassLoader().getResourceAsStream("InternetMemes.json");
	            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            
	            
	            StringBuilder builder = new StringBuilder();
	            
	            while (bufferedReader.ready())
	            {
	            	builder.append(bufferedReader.readLine());
	            }
	 
	            
	            InternetMemes internetMemesList = new ObjectMapper().readValue(builder.toString(),InternetMemes.class);
	            
	            sortInternetMemesListOnName(internetMemesList.getMemes());
	            
	            createLulzScore(internetMemesList.getMemes());
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	}
	
	public void sortInternetMemesListOnName(List<InternetMeme> list) {
		
		Comparator<InternetMeme> nameComparator = new Comparator<InternetMeme>(){

		    @Override
		    public int compare(final InternetMeme o1, final InternetMeme o2){
		         return o1.getName().compareTo(o2.getName());
		    }
		};
		
		Collections.sort(list, nameComparator);
		
		System.out.println(" Printing the sorted list >> ");
		
		for(InternetMeme meme : list) {
			
			System.out.println(meme.getName());
		}
		
	}
	
	public void createLulzScore(List<InternetMeme> list) {
		
		
		int lulzScore = 0;
		
		for(InternetMeme meme : list) {
			
			lulzScore = lulzScore+1;
			
			meme.setLulzScore(lulzScore);
		
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		 try
	      {
	         mapper.defaultPrettyPrintingWriter().writeValue(new File("InternetMemes.json"), list);
	      } catch (JsonGenerationException e)
	      {
	         e.printStackTrace();
	      } catch (JsonMappingException e)
	      {
	         e.printStackTrace();
	      } catch (IOException e)
	      {
	         e.printStackTrace();
	      }
		
		
		
	}
	
	public static void main(String[] args) {
		
		new InternetMemesUtil().readJsonFile();
		
	}

}
