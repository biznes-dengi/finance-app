import {cloneElement, ReactElement, ReactNode} from 'react';
import {NavigateFunction, useNavigate} from 'react-router-dom';

import {Icon} from '@shared/ui';

import {cn, styleElement} from '@shared/lib';

type Props = {
	name: ReactNode;
	description?: ReactNode;
	subDescription?: ReactNode;
	rightName?: ReactNode;
	rightDescription?: ReactNode;
	rightSubDescription?: ReactNode;
	isNameText?: boolean;

	icon?: ReactElement;
	statusIcon?: ReactElement;

	checked?: boolean;
	withMultipleSelection?: boolean;
	added?: boolean;
	showAddIcon?: boolean;

	rightNode?: ReactElement;
	withChevron?: boolean;
	onClick?: (navigate: NavigateFunction) => void;
};

export function Item(props: Props) {
	const {
		name,
		description,
		subDescription,
		rightName,
		rightDescription,
		rightSubDescription,
		isNameText,

		icon,
		statusIcon,

		checked,
		withMultipleSelection,
		added,
		showAddIcon,

		rightNode,
		withChevron,
		onClick,
	} = props;

	const navigate = useNavigate();

	const showIconCheckmark = !withMultipleSelection && icon && checked;
	const showRightCheckmark = !withMultipleSelection && !icon && checked;

	return (
		<div
			className={cn(
				'flex w-full rounded-2xl bg-white p-4 text-left shadow-[0_0_0_4px_white_inset] duration-300 hover:bg-light-grey',
				showIconCheckmark && 'bg-secondary-violet hover:bg-secondary-violet',
				onClick && 'cursor-pointer',
			)}
			onClick={() => onClick?.(navigate)}
		>
			{withMultipleSelection && (
				<div className='mr-4 flex flex-shrink-0 items-center'>
					<input type='checkbox' checked={checked} className='h-5 w-5' />
				</div>
			)}

			{icon && (
				<div className='relative my-0.5 mr-4 h-10 w-10 flex-shrink-0'>
					{icon && cloneElement(icon, {style: {height: '100%', borderRadius: '50%'}})}

					{(showIconCheckmark || statusIcon) && (
						<div
							className={cn(
								'size-5 absolute -bottom-1 -right-1 flex items-center justify-center rounded-full bg-primary-violet text-white',
								// 'shadow-[0_0_0_2px_white_inset]',
							)}
						>
							{styleElement(statusIcon || Icon.check, 'size-3.5')}
							{/*<div className='h-3 w-3'>{Icon.check}</div>*/}
						</div>
					)}
				</div>
			)}

			<div className={cn('min-w-0 flex-1 self-center')}>
				<div className={cn('truncate font-medium', isNameText && 'font-normal')}>{name}</div>
				{description && <div className='truncate text-sm font-light text-primary-grey'>{description}</div>}
				{subDescription && <div className='truncate text-sm font-light text-primary-grey'>{subDescription}</div>}
			</div>

			{(rightName || rightDescription || rightSubDescription) && (
				<div className={cn('ml-2 flex flex-shrink-0 flex-col items-end self-center', subDescription && 'self-stretch')}>
					{rightName && <div>{rightName}</div>}
					{rightDescription && <div className='text-sm font-light text-primary-grey'>{rightDescription}</div>}
					{rightSubDescription && <div className='text-sm font-light text-primary-grey'>{rightSubDescription}</div>}
				</div>
			)}

			{rightNode && <div className='ml-2 flex-shrink-0 self-center'>{rightNode}</div>}

			{showRightCheckmark && styleElement(Icon.check, 'size-5 text-primary-violet flex self-center')}

			{showAddIcon &&
				styleElement(
					added ? Icon.check : Icon.fund,
					cn('size-5 self-center', added ? 'text-primary-grey' : 'text-primary-violet'),
				)}

			{withChevron &&
				cloneElement(Icon.rightChevron, {className: 'ml-2 size-4 flex-shrink-0 self-center text-primary-grey'})}
		</div>
	);
}
