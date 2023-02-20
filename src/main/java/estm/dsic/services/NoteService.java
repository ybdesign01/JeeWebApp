package estm.dsic.services;

import estm.dsic.dal.NoteDao;
import estm.dsic.dal.UserDao;
import estm.dsic.models.Note;
import estm.dsic.models.User;

import java.util.Vector;

public class NoteService {
	private UserDao userdao=new UserDao();
	private NoteDao noteDao=new NoteDao();
	public Vector<Note> getNotes(String email) {
		return noteDao.getNotes(email);
	}

	public Note getNote(String id) {
		return noteDao.getNote(id);
	}

	public boolean update(Note note) {
		return noteDao.update(note);
	}

	public boolean add(Note note) {
		return noteDao.add(note);
	}

	public boolean remove(Integer note) {
		return noteDao.delete(note);
	}
}
