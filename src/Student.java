import java.util.*;
public class Student implements Comparable {
	public String nume;
	public String prenume;
	public int grupa;
	public ArrayList<Curs> cursuriInscrisi;

	public Student() { }
	Student(String nume, String prenume, int grupa) {
		this.nume = nume;
		this.prenume = prenume;
		this.grupa = grupa;

	}
	
	Student(ArrayList<String> properties) throws Exception {
		if ( properties.size() != 3 ) {
			throw new Exception("Invalid number of properties! The student cannot be created!");
		} else {
			this.nume = properties.get(0);
			this.prenume = properties.get(1);
			this.grupa = Integer.parseInt(properties.get(2));
		}
	}
	@Override
	public String toString() {
		return "Student [nume=" + nume + ", prenume=" + prenume + ", grupa=" + grupa + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + grupa;
		result = prime * result + ((nume == null) ? 0 : nume.hashCode());
		result = prime * result + ((prenume == null) ? 0 : prenume.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (nume == null) {
			if (other.nume != null)
				return false;
		} else if (!nume.equals(other.nume))
			return false;
		if (prenume == null) {
			if (other.prenume != null)
				return false;
		} else if (!prenume.equals(other.prenume))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		Student s = (Student)o;
		return this.nume.compareTo(s.nume);
	}

	public double medieAnUniversitar(Student s, int an)
	{

		ArrayList<Curs> cursuriStudent = s.cursuriInscrisi;
		double suma =0;
		double nr =0;
		for(Curs c: cursuriStudent)
		{
			if(c.an == an)
			{
				int nota = c.getNotaStudent(s);
				if(nota!=-1)
				{
					suma+=nota;
					nr++;
				}
			}
		}
		if(suma == 0)
		{
			return -1;
		}
		return suma/nr;
	}

	public ArrayList<Curs> getCursuriRestante()
	{
		ArrayList<Curs> restante = new ArrayList<Curs>();
		for(Curs c:cursuriInscrisi)
		{
			if(c.getNotaStudent(this)<5)
			{
				restante.add(c);
			}
		}
		return restante;
	}

	public void afisareRestante()
	{
		Scanner sc = new Scanner(System.in);
		boolean restanta = false;
		System.out.println("Restante:");
		for(Curs c:cursuriInscrisi)
		{
			if(c.getNotaStudent(this)<5 && c.getNotaStudent(this)!=-1)
			{
				c.AfisareNumeCurs();
				restanta = true;
			}
		}

		if(!restanta) {
			System.out.println("Felicitari! Nu aveti nicio restanta!!");
		}
	}

	public ArrayList<String> getCursuriNumeInscrisi()
	{
		ArrayList<String> numeCursuri = new ArrayList<String>();
		for(Curs c:cursuriInscrisi)
		{
			numeCursuri.add(c.nume);
		}
		return numeCursuri;
	}

}
