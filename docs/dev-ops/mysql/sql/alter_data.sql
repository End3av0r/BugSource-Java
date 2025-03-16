-- 修改原来的字段名称为bak
ALTER TABLE vulnerability
    CHANGE COLUMN pub_date pub_date_bak DATE;

-- 添加一个新的 DATE 类型字段
ALTER TABLE vulnerability
    ADD COLUMN pub_date DATE;

-- 将 VARCHAR 类型的数据转换为 DATE 类型并插入到新字段中
UPDATE vulnerability
SET pub_date = STR_TO_DATE(pub_date_bak, '%Y-%m-%d');

-- 删除原有的 VARCHAR 类型字段
ALTER TABLE vulnerability
    DROP COLUMN pub_date_bak;
