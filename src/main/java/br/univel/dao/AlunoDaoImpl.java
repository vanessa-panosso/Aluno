package br.univel.dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.univel.model.Aluno;
/**
 * É um objeto gerenciado.
 * Usado quando a instacia não faz diferença para quem está chamando
 * @author VLBPANOSSO
 * pq ter servidor aplicação: vc pode delegar funções para ele 
 * ex gerenciamento de cox bd
 * ex gerenciamento de memoria atravez pool de conexões
 * ex gerenciamento de thread
 */
@Stateless
@Remote(AlunoDao.class)
public class AlunoDaoImpl implements AlunoDao {
	
	/**
	 * Gerenciador de entidade
	 */
	@PersistenceContext(unitName="AlunoServer-persistence-unit")
	EntityManager em;
	
	/* (non-Javadoc)
	 * @see br.unive.dao.AlunoDaoIf#salvar(br.univel.model.Aluno)
	 */
	@Override
	public Aluno salvar(Aluno aluno) {
		em.persist(aluno);
		return aluno;
	}
	
	/* (non-Javadoc)
	 * @see br.unive.dao.AlunoDaoIf#getTodosAlunos()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Aluno> getTodosAlunos() {
		Query q = em.createNamedQuery("from Aluno");
		
		return q.getResultList();
	}
}
