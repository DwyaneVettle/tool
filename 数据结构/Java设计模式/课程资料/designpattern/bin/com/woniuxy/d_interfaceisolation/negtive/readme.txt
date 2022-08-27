接口隔离原则：接口应该建立在最小原则之上！

比如:
interface IDao {
	void save(User user);
	void save(Book book);
	void save(Orders orders);
	...
}