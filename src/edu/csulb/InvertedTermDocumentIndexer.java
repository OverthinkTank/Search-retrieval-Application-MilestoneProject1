package edu.csulb;

import cecs429.documents.DirectoryCorpus;
import cecs429.documents.Document;
import cecs429.documents.DocumentCorpus;
import cecs429.index.Index;
import cecs429.index.InvertedIndex;
import cecs429.index.Posting;
//import cecs429.index.TermDocumentIndex;
import cecs429.text.BasicTokenProcessor;
import cecs429.text.EnglishTokenStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;

public class InvertedTermDocumentIndexer {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the directory path: ");
		String path = sc.next();
		System.out.println("Your current directory is : " +path);
		DocumentCorpus corpus = DirectoryCorpus.loadJsonDirectory(Paths.get(path).toAbsolutePath(),".json");
		Index index = indexCorpus(corpus) ;
		// We aren't ready to use a full query parser; for now, we'll only support single-term queries.
	//	String query = "whale"; // hard-coded search for "whale"
		System.out.println("Enter term to search: ");
		String query=sc.next();
		
			for (Posting p : index.getPostings(query)) {
			System.out.println("Document " + corpus.getDocument(p.getDocumentId()).getTitle());
		}
	}
	
	private static Index indexCorpus(DocumentCorpus corpus) throws IOException {
		HashSet<String> vocabulary = new HashSet<>();
		BasicTokenProcessor processor = new BasicTokenProcessor();
		
		// First, build the vocabulary hash set.
		
		// TODO:
		// #Get all the documents in the corpus by calling GetDocuments().
		// #Iterate through the documents, and:
		// #Tokenize the document's content by constructing an EnglishTokenStream around the document's content.
		// #Iterate through the tokens in the document, processing them using a BasicTokenProcessor,
		// #and adding them to the HashSet vocabulary.
		
		
		for(Document d : corpus.getDocuments()) {
			EnglishTokenStream ets = new EnglishTokenStream(d.getContent());
			//
			for(String Token : ets.getTokens()) {
			// read one word at a time; process and add it to vocabulary.
						String word = processor.processToken(Token);
					//	if (word.length() > 0) {
							vocabulary.add(word);
					}
				}
				
		InvertedIndex index = new InvertedIndex(vocabulary, corpus.getCorpusSize());
		
		for(Document d : corpus.getDocuments()) {
			EnglishTokenStream ets = new EnglishTokenStream(d.getContent());
			
			for(String Token : ets.getTokens())
			{
			
						String word = processor.processToken(Token);
						index.addTerm(word,d.getId());
			}
			ets.close();
		}
				
		return index;
	}
}
