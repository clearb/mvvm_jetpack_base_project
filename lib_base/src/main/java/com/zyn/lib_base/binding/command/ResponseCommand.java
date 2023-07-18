package com.zyn.lib_base.binding.command;

import io.reactivex.functions.Function;

/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:About : kelin的ResponseCommand
 *  执行的命令事件转换
 * 创建者 @author:YangJiangLin[PHONE：181****4380]
 * 创建者邮箱:307806546@qq.com
 * 创建时间:2023/2/17
 * 项目名称:TsenfBase
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 ************************************************
 */
public class ResponseCommand<T, R> {

    private BindingFunction<R> execute;
    private Function<T, R> function;
    private BindingFunction<Boolean> canExecute;

    /**
     * like {@link BindingCommand},but ResponseCommand can return result when command has executed!
     *
     * @param execute function to execute when event occur.
     */
    public ResponseCommand(BindingFunction<R> execute) {
        this.execute = execute;
    }


    public ResponseCommand(Function<T, R> execute) {
        this.function = execute;
    }


    public ResponseCommand(BindingFunction<R> execute, BindingFunction<Boolean> canExecute) {
        this.execute = execute;
        this.canExecute = canExecute;
    }


    public ResponseCommand(Function<T, R> execute, BindingFunction<Boolean> canExecute) {
        this.function = execute;
        this.canExecute = canExecute;
    }


    public R execute() {
        if (execute != null && canExecute()) {
            return execute.call();
        }
        return null;
    }

    private boolean canExecute() {
        if (canExecute == null) {
            return true;
        }
        return canExecute.call();
    }


    public R execute(T parameter) throws Exception {
        if (function != null && canExecute()) {
            return function.apply(parameter);
        }
        return null;
    }
}
