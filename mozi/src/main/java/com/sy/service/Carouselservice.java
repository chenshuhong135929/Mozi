package com.sy.service;

import com.sy.pojo.Carousel;
import com.sy.utils.PageModel;

public interface Carouselservice {
	
	/**添加
	 * @param c
	 * @return
	 */
	public boolean addCarousel(Carousel c);
	
	/**删除
	 * @param id
	 * @return
	 */
	public boolean deleteCarousel(Integer id);
	
	/**修改
	 * @param c
	 * @return
	 */
	public boolean updateCarousel(Carousel c);
	
	/**分页获取数据
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	PageModel<Carousel>getusersone(Integer pageNo,String keyword);
	
	/**根据id获取数据
	 * @param id
	 * @return
	 */
	public Carousel getCarousel(Integer id);

}
